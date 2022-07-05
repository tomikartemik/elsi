package com.komandor.komandor.data.api.model.request

data class AuthRequest(
    val cert: String,
    val sign: String
) {
}