package com.komandor.komandor.data.api.model.request
//"data": "data:image/png;base64,AAAAAA=="
class UpdatePhotoRequest(
    val data:String
)

/*
Для удаления фото и замены его на стандартную иконку необходимо передать пустое значениеdata: ''

{
  "data": ""
}
 */