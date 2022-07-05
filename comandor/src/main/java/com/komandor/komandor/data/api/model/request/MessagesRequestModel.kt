package com.komandor.komandor.data.api.model.request

/*
{
  "chatId?": "(string) ID чата",
  "folderId?": "(string) ID папки",
  "messageId?": "(timestamp) ID сообщения (врем создания сообщения)",
  "rel?": "(string) Направления запроса: prev|next",
  "type?": "(string) Тип сообщения: file"
}
 */
data class MessagesRequestModel(
    var chatId: String?,
    var folderId: String?,
    var messageId: Long?,
    var rel: String?,
    var type: String?,

    )