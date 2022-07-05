package com.komandor.komandor.data.api.model.response

import com.google.gson.annotations.SerializedName

data class FileResponse(
    @SerializedName("content")
    val content: String? = null
) {
}