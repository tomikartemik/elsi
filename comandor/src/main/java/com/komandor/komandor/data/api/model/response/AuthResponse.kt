package com.komandor.komandor.data.api.model.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(

    @SerializedName("nonce")
    val nonce: String,

    @SerializedName("token")
    val token: String
) {
}