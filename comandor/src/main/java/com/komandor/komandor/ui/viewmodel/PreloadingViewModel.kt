package com.komandor.komandor.ui.viewmodel

import android.content.Context
import android.provider.Settings
import androidx.lifecycle.viewModelScope
import com.atom.ui.viewmodel.BaseViewState
import com.komandor.komandor.R
import com.komandor.komandor.cryptopro.CryproProManager
import com.komandor.komandor.data.api.KomandorAPI
import com.komandor.komandor.data.api.model.request.RegistryFCMRequestModel
import com.komandor.komandor.data.database.KomandorDatabase
import com.komandor.komandor.data.database.model.LocalCertificate
import com.komandor.komandor.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PreloadingViewModel @Inject constructor(
    val cryproProManager: CryproProManager,
    val api:KomandorAPI,
    val db: KomandorDatabase

) : BaseViewModel(BaseViewState(isLoading = false)) {

    init {
        // тестовый контейнер из RAW
        //cryproProManager.installContainersfromRes( R.raw.keys)
    }

    fun hasUserContainers(context: Context) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            delay(2000);
            val isIntegritySuccess = cryproProManager.checkIntegrity(false)
            val isLicenseSuccess = cryproProManager.checkLicense(false)
            //cryproProManager.checkConfig()
            val r =
                cryproProManager.isCheckKeysDirectory(context)//cryproProManager.checkContainer()

            emitComplete(isIntegritySuccess && isLicenseSuccess && r)
        }
    }

    fun registerFcm(context:Context, token:String){
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            val secureId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)

            val eegistryFCMRequestModel = RegistryFCMRequestModel(
                device = secureId,
                fcm = token
            )

            api.registryFCM(eegistryFCMRequestModel).collect {
                Timber.d("registryFCM = ${it}")
                emitComplete(null)
            }

        }

    }

}