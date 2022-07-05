package com.komandor.komandor.data.api.model.request

/*
{
  "device": "(string) Идентификатор устройства",
  "fcm": "(string) Токен FCM",
  "type": "(number) Тип устройства: 1 - iOS, 2 - Android, 3 - Huawei (HMS)"
}
 */
data class RegistryFCMRequestModel(
    val device: String,
    val fcm: String,
    val type: Int = 2,
)