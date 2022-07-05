package com.komandor.komandor.data.api.model.response

import com.google.gson.annotations.SerializedName
/*
      "chatKey": {
        "id": "(string) ID ключа",
        "key": "(base64) Сессионный ключ"
      },
 */
data class ChatKey(
    @SerializedName("id")
    var id: String,

    @SerializedName("key")
    var key: String,


    )