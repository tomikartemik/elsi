package com.komandor.komandor.cryptopro.model

data class EncryptedFile(
    val cid: String,
    val encryptedName: String,
    val nameSign: String,
    val encryptedData: String,
    val cms: String
)