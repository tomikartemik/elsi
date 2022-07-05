package com.komandor.komandor.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.komandor.komandor.data.api.model.Billing
import com.komandor.komandor.data.api.model.Traiffs
import com.komandor.komandor.data.api.model.response.CertificateResponse
import com.komandor.komandor.data.api.model.response.ChatKey
import com.komandor.komandor.extension.encodeCertificate
import com.komandor.komandor.extension.toCertificate
import java.security.cert.X509Certificate
import java.util.*

class DateTypeConverter {
    private val gson: Gson

    init {
        gson = Gson()
    }

    @TypeConverter
    fun toDate(value: Long?): Date? = if (value == null) null else Date(value)

    @TypeConverter
    fun toLong(value: Date?): Long? = value?.time

    @TypeConverter
    fun toBilling(value: Billing?): String? {
        return if (value == null) null else gson.toJson(value)

    }

    @TypeConverter
    fun fromBilling(value: String?): Billing? {
        return if (value == null) null
        else
            gson.fromJson<Billing>(value, object : TypeToken<Billing>() {}.type)
    }

    @TypeConverter
    fun listTraiffsFromString(value: String?): List<Traiffs>? {
        return if (value == null) null else gson.fromJson<List<Traiffs>>(
            value,
            object : TypeToken<List<Traiffs>?>() {}.type
        )
    }


    @TypeConverter
    fun listTraiffsToString(value: List<Traiffs>?): String? {
        return if (value == null) null else gson.toJson(value)
    }


    @TypeConverter
    fun listCertificateResponseFromString(value: String?): List<CertificateResponse>? {
        return if (value == null) null else gson.fromJson<List<CertificateResponse>>(
            value,
            object : TypeToken<List<CertificateResponse>?>() {}.type
        )
    }


    @TypeConverter
    fun listCertificateResponseToString(value: List<CertificateResponse>?): String? {
        return if (value == null) null else gson.toJson(value)
    }

    @TypeConverter
    fun toCertificateResponse(value: CertificateResponse?): String? {
        return if (value == null) null else gson.toJson(value)

    }

    @TypeConverter
    fun fromCertificateResponse(value: String?): CertificateResponse? {
        return if (value == null) null
        else
            gson.fromJson<CertificateResponse>(value, object : TypeToken<CertificateResponse>() {}.type)
    }

    @TypeConverter
    fun toX509Certificate(value: X509Certificate?): String? {
        return if (value == null) null else value.encodeCertificate()

    }

    @TypeConverter
    fun fromX509Certificate(value: String?): X509Certificate? {
        return if (value == null) null
        else
           value.toCertificate()
    }

    @TypeConverter
    fun toChatKeye(value: ChatKey?): String? {
        return if (value == null) null else gson.toJson(value)

    }

    @TypeConverter
    fun fromChatKey(value: String?): ChatKey? {
        return if (value == null) null
        else
            gson.fromJson<ChatKey>(value, object : TypeToken<ChatKey>() {}.type)
    }
}
