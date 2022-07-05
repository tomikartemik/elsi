package com.komandor.komandor.data.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.komandor.komandor.data.api.model.Billing
import com.komandor.komandor.data.api.model.Traiffs
import com.komandor.komandor.data.api.model.response.CertificateResponse
import kotlinx.android.parcel.Parcelize
import javax.annotation.concurrent.Immutable

@Entity(tableName = "ACCOUNTS", indices = [(Index(value = ["pid"], unique = true))])
@Parcelize
@Immutable
data class User(
    @PrimaryKey
    val pid: String,

    val cid: String,
    val type: Int? = null,
    val name: String? = null,
    val company: String? = null,
    val title: String? = null,
    val inn: String? = null,
    val orgn: String? = null,
    val snils: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val photo: String? = null,
    val certificates: List<CertificateResponse>? = null,
    val billing: Billing? = null,
    val tariffs:  List<Traiffs>? = null,
    val sync: Boolean? = null,
    var current:Boolean  = false

) : Parcelable{
}