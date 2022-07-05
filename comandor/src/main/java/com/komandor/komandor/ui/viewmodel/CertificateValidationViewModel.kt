package com.komandor.komandor.ui.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.atom.ui.viewmodel.BaseViewState
import com.komandor.komandor.cryptopro.CryproProManager
import com.komandor.komandor.data.api.KomandorAPI
import com.komandor.komandor.data.database.KomandorDatabase
import com.komandor.komandor.data.database.model.LocalCertificate
import com.komandor.komandor.ui.model.ListCertificateItem
import com.komandor.komandor.di.TemporaryStorage
import com.komandor.komandor.extension.getClientName
import com.komandor.komandor.extension.getCompanyName
import com.komandor.komandor.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.security.cert.X509Certificate
import javax.inject.Inject

@HiltViewModel
class CertificateValidationViewModel @Inject constructor(
    val cryproProManager: CryproProManager,
    val api: KomandorAPI,
    val temporaryStorage: TemporaryStorage,
    val db: KomandorDatabase


) : BaseViewModel(BaseViewState(isLoading = false)) {

    fun isCurrentCertificate() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            var currentCertificate: LocalCertificate? = db.localCertificateDao().getCurrent()

            Timber.d("currentCertificate key = ${currentCertificate?.key} current = ${currentCertificate?.current}")
            emitComplete(currentCertificate)
        }
    }

    fun initLocalCertificates() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            var certificates: Map<String, X509Certificate>? =
                cryproProManager.getLocalCertificates()
            Timber.d("certificates = ${certificates?.size}")
            val listLocalCertificate: List<LocalCertificate> = certificates?.map {
                LocalCertificate(it.key, it.value)
            } ?: listOf()
            db.localCertificateDao().insertAll(listLocalCertificate)
            val listCertificates: List<ListCertificateItem> =
                db.localCertificateDao().getAll().map {
                    val clientName = it.certificate.getClientName()
                    val companyName = it.certificate.getCompanyName()
                    //Timber.d("clientName = ${clientName} companyName = $companyName")
                    ListCertificateItem(it.key, it.certificate, clientName, companyName)
                }

            emitComplete(listCertificates)
        }
    }


    fun deleteCertificate(context: Context) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            var certificates: Map<String, X509Certificate>? =
                cryproProManager.getLocalCertificates()
            certificates?.let {
                it.keys.forEach { alias -> cryproProManager.deleteContainer(alias) }
                emitComplete(null)
            }
        }
    }
}