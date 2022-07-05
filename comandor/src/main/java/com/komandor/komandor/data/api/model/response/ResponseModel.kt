package com.komandor.komandor.data.api.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseModel<T>(

    @SerializedName("success") @Expose
    val success: Boolean,
    @SerializedName("error")
    @Expose
    val error: String = "",

    @SerializedName("data")
    @Expose
    val data: T? = null
) {
}