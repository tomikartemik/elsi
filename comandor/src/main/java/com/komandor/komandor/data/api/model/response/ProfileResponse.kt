package com.komandor.komandor.data.api.model.response

import com.google.gson.annotations.SerializedName
import com.komandor.komandor.data.api.model.Billing
import com.komandor.komandor.data.api.model.Traiffs

data class ProfileResponse(
    @SerializedName("pid")
    val pid: String,

    @SerializedName("cid")
    val cid: String,

    @SerializedName("type")
    val type: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("company")
    val company: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("inn")
    val inn: String? = null,

    @SerializedName("orgn")
    val orgn: String? = null,

    @SerializedName("snils")
    val snils: String? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("photo")
    val photo: String? = null,

    @SerializedName("certificates")
    val certificates: List<CertificateResponse>? = null,

    @SerializedName("billing")
    val billing: Billing? = null,

    @SerializedName("tariffs")
    val tariffs:  List<Traiffs>? = null,

    @SerializedName("sync")
    val sync: Boolean? = null

) {
}