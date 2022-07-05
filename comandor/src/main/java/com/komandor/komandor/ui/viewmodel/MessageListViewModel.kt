package com.komandor.komandor.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.atom.ui.viewmodel.BaseViewState
import com.komandor.komandor.cryptopro.CryproProManager
import com.komandor.komandor.cryptopro.model.DecryptedSessionKey
import com.komandor.komandor.data.api.KomandorAPI
import com.komandor.komandor.data.api.model.EncryptedSessionKey
import com.komandor.komandor.data.api.model.request.MessagesRequestModel
import com.komandor.komandor.data.database.KomandorDatabase
import com.komandor.komandor.data.database.model.Chat
import com.komandor.komandor.data.database.model.LocalCertificate
import com.komandor.komandor.data.database.model.Message
import com.komandor.komandor.data.database.model.User
import com.komandor.komandor.data.mapper.toMessage
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
class MessageListViewModel @Inject constructor(
    val cryproProManager: CryproProManager,
    val api: KomandorAPI,
    val temporaryStorage: TemporaryStorage,
    val db: KomandorDatabase
) : BaseViewModel(BaseViewState(isLoading = false)) {

    lateinit var user: User
    lateinit var chat:Chat
    lateinit var localCertificate: LocalCertificate

    fun allMessages(): Flow<List<Message>> = db.messageDao().getAll()

    fun getMessages(chatId:String){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            db.chatDao().getById(chatId)?.let { chat = it }
            Timber.d("chat = ${chat}")
            db.userDao().getCurrent()?.let { user = it }
            Timber.d("user = ${user.name}")
            db.localCertificateDao().getCurrent()?.let { localCertificate = it }
            Timber.d("localCertificate key = ${localCertificate.key} sigAlgName = ${localCertificate.certificate.sigAlgName}")

            val messagesRequestModel = MessagesRequestModel(
                chatId = chatId,
                null,
                null,
                null,
                null,
            )

            chat.chatKey?.let { keyChat ->
                val encryptedSessionKey = EncryptedSessionKey(encryptedKey = keyChat.key, certificate = localCertificate.certificate)
                Timber.d("keyChat.key = ${keyChat.key} encryptedSessionKey = ${encryptedSessionKey.encryptedKey}")
                try {

                    val decryptedSessionKey: DecryptedSessionKey? =
                        cryproProManager.decryptEphemeralSessionKey(encryptedSessionKey)
                    Timber.d("decryptedSessionKey = ${decryptedSessionKey?.key}")





                    api.getMessages(messagesRequestModel).collect {
                        Timber.d("MessageResponse = ${it.data?.size}")
                        val messages = it.data?.map { item ->
                            Timber.d("MessageResponse item = ${item.keyId} ${item.text}")
                            var message = item.toMessage()
                            /*
                           var s = cryproProManager.decryptMessage(message, decryptedSessionKey)
                            Timber.d("decryptMessage = ${s}")

                             */


/*
                        val encryptedMessage: EncryptedMessage =
                            cryproProManager.encryptMessage(cid = user.cid , message = item.text?:"", encryptedSessionKey = encryptedSessionKey)
                        Timber.d("encryptedMessage = ${encryptedMessage}")

 */




                            message
                        } ?: emptyList()
                        db.messageDao().insertAll(messages)
                        emitComplete(messages)
                    }
                } catch (ex:Exception){
                    Timber.d("ex = $ex")
                    emitException(ex)
                }
            }
        }


    }



    fun sendMessage( messageText:String){
        chat.chatKey?.let {
            val sessionKey = EncryptedSessionKey(encryptedKey = it.key, certificate = localCertificate.certificate)
            /*
            val encryptedMessage: EncryptedMessage =
                cryproProManager.encryptMessage(user.cid, messageText, sessionKey)
            val message = Message(it.id, temporaryStorage.userID, encryptedMessage)

             */

            //val localID: Long = messagesStorage.insertLocalMessage(message)
            //chatsStorage.setLastMessageID(message.getChatID(), localID)
            //socketAPI.sendMessage(localID, message)
        }
    }


    fun sendFile(){

    }

}