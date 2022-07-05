package com.komandor.komandor.cryptopro.model

import javax.crypto.SecretKey

class DecryptedSessionKey(val key: SecretKey, val IV: ByteArray)