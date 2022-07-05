package com.komandor.komandor.data.api.model.response

import com.google.gson.annotations.SerializedName

data class CertificateRegistrationResponse(
    @SerializedName("id")
    var id: String,
    @SerializedName("cert")
    var cert: String,
    @SerializedName("cert")
    var sync: Boolean,

)