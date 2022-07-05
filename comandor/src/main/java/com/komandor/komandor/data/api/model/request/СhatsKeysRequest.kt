package com.komandor.komandor.data.api.model.request
/*
[
  {
    "chatId": "string",
    "cid": "string",
    "pid": "string",
    "key": "string",
    "sign": "string"
  }
 */
data class СhatsKeysRequest(
    val chatId:String,
    val cid:String,
    val pid:String,
    val key:String,
    val sign:String,

    )