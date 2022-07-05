package com.komandor.komandor.data.database.model


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.komandor.komandor.data.JSONConvertable
import java.security.cert.X509Certificate
import javax.annotation.concurrent.Immutable

@Entity(tableName = "LocalCertificate", indices = [(Index(value = ["key"], unique = true))])
@Immutable
data class LocalCertificate(
    @PrimaryKey
    val key: String,
    val certificate: X509Certificate,
    var current:Boolean = false

) : JSONConvertable {
}