package com.komandor.komandor.di

class TemporaryStorage {
    var token: String? = null
    var userID = 0



    fun clearStorage() {
        token = null
        userID = -1
    }

}