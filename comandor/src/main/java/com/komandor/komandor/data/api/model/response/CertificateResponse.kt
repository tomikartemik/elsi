package com.komandor.komandor.data.api.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CertificateResponse(
    @SerializedName("pid")
    var pid: String?,
    @SerializedName("cid")
    var cid: String,

    @SerializedName("certificate")
    var certificate: String? = null,

    @SerializedName("hash")
    var hash: String? = null


) : Parcelable