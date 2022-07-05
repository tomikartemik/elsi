package com.komandor.komandor.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.atom.ui.viewmodel.BaseViewState
import com.komandor.komandor.cryptopro.CryproProManager
import com.komandor.komandor.data.api.KomandorAPI
import com.komandor.komandor.data.api.model.request.ChatsRequest
import com.komandor.komandor.data.database.KomandorDatabase
import com.komandor.komandor.data.database.model.Chat
import com.komandor.komandor.data.database.model.User
import com.komandor.komandor.data.mapper.toChat
import com.komandor.komandor.data.mapper.toUser
import com.komandor.komandor.di.TemporaryStorage
import com.komandor.komandor.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class ChatsViewModel @Inject constructor(
    val cryproProManager: CryproProManager,
    val api: KomandorAPI,
    val temporaryStorage: TemporaryStorage,
    val db: KomandorDatabase
) : BaseViewModel(BaseViewState(isLoading = false)) {

    fun allChats(): Flow<List<Chat>> = db.chatDao().getAll()


    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            api.profile().collect { profileResponse ->
                if (profileResponse.success) {
                    profileResponse.data?.let {
                        cryproProManager.currentUser?.let{
                            it.current = false
                            db.userDao().update(it)
                        }

                        val user: User = it.toUser()
                        user.current = true;
                        db.userDao().insert(user)
                        cryproProManager.currentUser = user
                        emitComplete(user)
                    }
                } else {
                    emitException(Exception(profileResponse.error))
                }
            }
        }
    }

    fun getChats() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            val user = db.userDao().getCurrent()
            //Timber.d("user = ${user?.sync}")
            if (user?.sync ?: false) {
                api.chats(ChatsRequest()).collect {
                    if (it.success) {
                        Timber.d("chats  = ${it.data?.size}")
                        val  chatList = it.data?.map {item -> item.toChat()}?: emptyList()
                        db.chatDao().insertAll(chatList)
                        emitComplete(db.chatDao().getAll())
                    } else {
                        emitException(Exception(it.error))
                    }
                    //chatsKeysRequest:List<СhatsKeysRequest>
                }
            } else {

            }

/*
                    api.chatsKeys().collect {
                        Timber.d("chatsKeys  = ${it.data}")

                    }
                    api.certificateRegistration().collect {
                        Timber.d("certificateRegistration  = ${it.data}")

                    }

 */


        }
    }


}