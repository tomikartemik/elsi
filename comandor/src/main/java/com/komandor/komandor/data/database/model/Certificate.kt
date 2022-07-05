package com.komandor.komandor.data.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.komandor.komandor.data.api.model.response.CertificateResponse
import kotlinx.android.parcel.Parcelize
import javax.annotation.concurrent.Immutable

@Parcelize
@Immutable
@Entity(tableName = "CERTIFICATES", indices = [(Index(value = ["pid"], unique = true))])
data class Certificate(
    @PrimaryKey

    val pid:Int,
    val certificate: CertificateResponse,
    val isLocal:Boolean = false,
    val isActive:Boolean = false
    ) : Parcelable {


    @Ignore
    val cid = certificate.cid
    @Ignore val base64 = certificate.certificate
}