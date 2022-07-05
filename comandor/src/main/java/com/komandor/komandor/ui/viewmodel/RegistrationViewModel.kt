package com.komandor.komandor.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.atom.ui.viewmodel.BaseViewState
import com.komandor.komandor.cryptopro.CryproProManager
import com.komandor.komandor.data.api.KomandorAPI
import com.komandor.komandor.data.api.model.request.RegisrtryPhoneRequest
import com.komandor.komandor.di.TemporaryStorage
import com.komandor.komandor.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    val cryproProManager: CryproProManager,
    val api: KomandorAPI,
    val temporaryStorage: TemporaryStorage
) : BaseViewModel(BaseViewState(isLoading = false)) {

    private var codeID: Int? = null

    fun isValidCode(smsCode: String): Boolean {
        return !smsCode.isEmpty()
    }

    fun isValidPhone(phoneNumber: String): Boolean {
        return phoneNumber.length > 3
    }


    fun confirmPhone(code: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            if (temporaryStorage.token != null && codeID != null) {
                api.confirmPhone(code).collect {
                    Timber.d("registryPhone response = ${it}")
                    emitComplete(null)
                }
            } else {
                emitException(Exception("Token null"))
            }
        }
    }


    fun registryPhone(phone: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            api.registryPhone(RegisrtryPhoneRequest("+${phone}")).collect {
                Timber.d("registryPhone response = ${it}")
                if (it.success) {
                    it.data?.let { d -> codeID = d.codeID }
                    emitComplete(null)
                } else {
                    emitException(Exception(it.error))
                }
            }
        }
    }
}