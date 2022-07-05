package com.komandor.komandor.data.api.model.response

import com.google.gson.annotations.SerializedName

data class ContactResponse(

    @SerializedName("uid")
    var uid: Int ,

    @SerializedName("pid")
    val pid: Int ,

    @SerializedName("user")
    val fio: String? = null,

    @SerializedName("name")
    val company: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("phone")
    val phone: String? = null,

    @SerializedName("photo")
    val photo: String? = null,

    @SerializedName("type")
    val type: Int? = null,

    @SerializedName("certificates")
    val certificates: List<CertificateResponse>,

    @SerializedName("chatId")
    val chatId: String? = null,

    @SerializedName("chatType")
    val chatType: Int? = null,

    @SerializedName("chatName")
    val chatName: String? = null,

    @SerializedName("key")
    val key: SecretKeyResponse? = null
) {
}