package com.komandor.komandor.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.viewModelScope
import com.atom.ui.viewmodel.BaseViewState
import com.komandor.komandor.cryptopro.CryproProManager
import com.komandor.komandor.data.api.KomandorAPI
import com.komandor.komandor.data.api.model.request.UpdatePhotoRequest
import com.komandor.komandor.data.database.KomandorDatabase
import com.komandor.komandor.data.database.model.User
import com.komandor.komandor.di.TemporaryStorage
import com.komandor.komandor.extension.toBase64
import com.komandor.komandor.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val cryproProManager: CryproProManager,
    val api: KomandorAPI,
    val temporaryStorage: TemporaryStorage,
    val db: KomandorDatabase

) : BaseViewModel(BaseViewState(isLoading = false)) {

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            val user: User? = db.userDao().getCurrent()
            emitComplete(user)
        }
    }


    // Bitmap bm=((BitmapDrawable)imageView.getDrawable()).getBitmap();
    fun updatePhoto(bitmap: Bitmap) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            val updatePhotoRequest = UpdatePhotoRequest(bitmap.toBase64())
            api.updatePhoto(updatePhotoRequest).collect {
                if (it.success) {
                    emitComplete(bitmap)
                } else {
                    emitException(Exception(it.error))
                }
            }
        }
    }

    fun logout() {

    }
}