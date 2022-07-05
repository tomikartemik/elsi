package com.komandor.komandor.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONObject
import timber.log.Timber
import java.util.*

interface JSONConvertable {
    fun toJSON(): String = Gson().toJson(this)
}

inline fun <reified T : JSONConvertable> String.toObject(): T {
    val builder = GsonBuilder()
    val gson = builder.create()
    val obj = gson.fromJson(this, T::class.java)
    //Timber.d("this = ${this} toObject obj = ${obj}")
    return obj
}

inline fun <reified T : JSONConvertable> JSONObject.toObject(): T {
    return this.toString().toObject()

}
