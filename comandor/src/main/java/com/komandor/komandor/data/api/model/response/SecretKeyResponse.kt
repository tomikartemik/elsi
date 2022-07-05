package com.komandor.komandor.data.api.model.response

import com.google.gson.annotations.SerializedName

data class SecretKeyResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("key")
    val key: String? = null,

    @SerializedName("import")
    val import: Boolean = false,

    @SerializedName("cert")
    val cert: String? = null
) {
}