package com.komandor.komandor.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.atom.ui.viewmodel.BaseViewState
import com.komandor.komandor.cryptopro.CryproProManager
import com.komandor.komandor.data.api.KomandorAPI
import com.komandor.komandor.data.api.model.request.AuthRequest
import com.komandor.komandor.data.api.model.request.ConfirmAuthRequest
import com.komandor.komandor.data.database.KomandorDatabase
import com.komandor.komandor.data.database.model.LocalCertificate
import com.komandor.komandor.data.database.model.User
import com.komandor.komandor.data.mapper.toUser
import com.komandor.komandor.di.TemporaryStorage
import com.komandor.komandor.ui.base.BaseViewModel
import com.komandor.komandor.utils.SystemUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val cryproProManager: CryproProManager,
    val api: KomandorAPI,
    val temporaryStorage: TemporaryStorage,
    val db: KomandorDatabase


) : BaseViewModel(BaseViewState(isLoading = false)) {

    var localCertificate: LocalCertificate? = null


    fun getCertificate(key: String) {

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {

            emitProgress()
            localCertificate = db.localCertificateDao().getByKey(key)
            Timber.d("localCertificate key = ${localCertificate?.key} current = ${localCertificate?.current}")
            emitComplete(localCertificate)
        }
    }


    fun validatePassword(password: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            localCertificate?.let { _localCertificate ->
                val cert: String? =
                    cryproProManager.encodeCertificate(_localCertificate.certificate)
                try {
                    val sign: String? = cryproProManager.signCertificate(
                        _localCertificate.key,
                        "sECJY125", //"sECJY125", //TODO password,
                        _localCertificate.certificate
                    )


                    //Timber.d("CERT = ${cert}")
                    //Timber.d("SIGN = ${sign}")
                    if (cert != null && sign != null) {
                        emitProgress()
                        temporaryStorage.token = null
                        api.authentication(AuthRequest(cert, sign)).collect { authResponse ->
                            //Timber.d("authentication response = ${authResponse}")
                            if (!authResponse.success) {
                                emitException(Exception(authResponse.error))
                            } else {
                                authResponse.data?.token?.let { tk ->
                                    temporaryStorage.token = tk
                                    val sign: String? =
                                        cryproProManager.sign(SystemUtils.decodeBase64(authResponse.data.nonce))
                                    // Timber.d("authResponse sign = ${sign}")
                                    if (sign == null) {
                                        emitException(Exception("sign is null"))
                                    } else {
                                        api.confirmAuthentication(ConfirmAuthRequest(sign))
                                            .collect { confirmAuthenticationResponse ->
                                                //Timber.d("confirmAuthentication response data= ${confirmAuthenticationResponse.data}")
                                                if (confirmAuthenticationResponse.success) {
                                                    confirmAuthenticationResponse.data?.token?.let { tkConfirm ->
                                                        temporaryStorage.token = tkConfirm

                                                        if (!confirmAuthenticationResponse.data.needRegisterPhone) {
                                                            _localCertificate.current = true
                                                            db.localCertificateDao()
                                                                .update(_localCertificate)
                                                        }
                                                        emitComplete(confirmAuthenticationResponse.data.needRegisterPhone)
                                                    }
                                                } else {
                                                    emitException(
                                                        Exception(
                                                            confirmAuthenticationResponse.error
                                                        )
                                                    )
                                                }
                                            }
                                    }
                                }
                            }
                        }
                    }
                } catch (err: Exception) {
                    emitException(err)
                }
            }
        }
    }
}