package com.komandor.komandor.data.database.model

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "message_table", indices = [Index(value = ["messageId"], unique = true)])

data class Message(
    val chatId: String,
    @PrimaryKey
    val messageId: String,
    val prevMessageId: String?,
    val lastMessageId: String?,
    val pid: String,
    val cid: String,
    val keyId: String,
    val folderId: String?,
    val type: String,
    val user: String? = null,
    val text: String?,
    val textSign: String?,
    val fileId: String?,
    val fileSize: Long?,
    val fileSign: String?,
    val status: Int,
    val statusTime: Long,
) {
}