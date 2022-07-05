package com.komandor.komandor.data.api.model.response

import com.google.gson.annotations.SerializedName

data class ConfirmAuthResponse(
    @SerializedName("token")
    val token: String,

    @SerializedName("needRegisterPhone")
    val needRegisterPhone: Boolean
) {
}