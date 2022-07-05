package com.komandor.komandor.cryptopro.model

data class EncryptedMessage(val cid: String, val message: String, val sign: String)