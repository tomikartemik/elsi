package com.komandor.komandor.data.api.model.response

import com.google.gson.annotations.SerializedName
/*
[{
  "chatId": "(string) ID чата",
  "messageId": "(timestamp) ID сообщения (врем создания сообщения)",
  "prevMessageId": "(timestamp | null) Предыдущий ID сообщения",
  "lastMessageId": "(timestamp | null) ID сообщения (врем создания последнего сообщения)",
  "pid": "(string) ID профиля отправителя",
  "cid": "(string) ID сертификата отправителя",
  "keyId": "(string) ID ключа",
  "folderId": "(string) ID папки",
  "type": "(string) Тип сообщения: text/file/etc",
  "user": "(string) ФИО отправителя",
  "text": "(base64) Зашифрованное текстовое сообщение или имя файла",
  "textSign": "(base64) Простая подпись для text",
  "fileId": "(uuid) ID файла в хранилище",
  "fileSize": "(number) Размер файла в байтах",
  "fileSign": "(base64) CMS подпись для файла",
  "status": "(number) Статус: 0 - новый, 1 - отправлен, 2 - доставлен, 3 - прочитан",
  "statusTime": "(timestamp) Время последнего статуса"
}]
 */
data class MessageResponse(

    @SerializedName("chatId")
    val chatId: String,

    @SerializedName("messageId")
    val messageId: String,

    @SerializedName("prevMessageId")
    val prevMessageId: String?,

    @SerializedName("lastMessageId")
    val lastMessageId: String?,

    @SerializedName("pid")
    val pid: String,

    @SerializedName("cid")
    val cid: String,

    @SerializedName("keyId")
    val keyId: String,

    @SerializedName("folderId")
    val folderId: String?,

    @SerializedName("type")
    val type: String,

    @SerializedName("user")
    val user: String? = null,

    @SerializedName("text")
    val text: String?,

    @SerializedName("textSign")
    val textSign: String?,

    @SerializedName("fileId")
    val fileId: String?,

    @SerializedName("fileSize")
    val fileSize: Long?,

    @SerializedName("fileSign")
    val fileSign: String?,

    @SerializedName("status")
    val status: Int,

    @SerializedName("statusTime")
    val statusTime: Long,
) {
}