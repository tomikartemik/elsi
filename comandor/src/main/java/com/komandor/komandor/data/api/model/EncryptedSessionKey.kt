package com.komandor.komandor.data.api.model


import java.security.cert.X509Certificate

data class EncryptedSessionKey(
    val certificate: X509Certificate,
    val encryptedKey: String

) {

    val isValid: Boolean
        get() = certificate != null && encryptedKey != "" && encryptedKey != null

}