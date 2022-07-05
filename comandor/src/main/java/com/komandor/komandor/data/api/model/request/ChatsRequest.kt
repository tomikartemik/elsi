package com.komandor.komandor.data.api.model.request

data class ChatsRequest (
    val phones:List<String>? = null,
    val chatIds:List<String>? = null,
)