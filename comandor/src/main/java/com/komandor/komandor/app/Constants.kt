package com.komandor.komandor.app

import android.os.Environment

object Constants {
    const val STORAGE_PERMISSIONS_RESULT = 10000
    const val CAMERA_PERMISSIONS_RESULT = 10001
    const val CAMERA_ACTIVITY_RESULT = 10002
    const val GALLERY_ACTIVITY_RESULT = 10003
    const val CONTACTS_PERMISSIONS_RESULT = 10004
    const val OPEN_FILE_RESULT = 10006
    const val FINISH_START_ACTIVITY = 11000
    const val FINISH_CHAT_ACTIVITY = 11001
    const val NO_TYPE = 0 // Нет типа контакта
    const val NP = 1 // Физ. лицо (natural person)
    const val SP = 2 // ИП
    const val LP = 3 // Юр. лицо (legal person)
    const val HEADER_CACHE_CONTROL = "Cache-Control"
    const val HEADER_PRAGMA = "Pragma"
    const val DEFAULT_CONTAINERS_STORAGE_DIRECTORY = "Komandor"
    @JvmField
    val DOWNLOADS_FOLDER =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + "/Komandor"
    const val cacheSize = (10 * 1024 * 1024).toLong()
    const val MESSAGE_TRANSITION_NAME = "MESSAGE_TN_"
    const val INTENT_EXTRA_CONTACT_ID = "INTENT_EXTRA_CONTACT_ID"
    const val INTENT_EXTRA_CHAT_ID = "INTENT_EXTRA_CHAT_ID"
    const val INTENT_EXTRA_MESSAGE = "INTENT_EXTRA_MESSAGE"
    const val INTENT_EXTRA_TRANSITION_NAME = "INTENT_EXTRA_TRANSITION_NAME"
    const val INTENT_EXTRA_TO_CHATS = "INTENT_EXTRA_TO_CHATS"
    const val INTENT_EXTRA_TO_PROFILE = "INTENT_EXTRA_TO_PROFILE"
    const val INTENT_EXTRA_TO_CONTACTS = "INTENT_EXTRA_TO_CONTACTS"
    const val INTENT_EXTRA_AFTER_RESTORATION = "INTENT_EXTRA_AFTER_RESTORATION"
    const val PREFERENCE_PASSWORD = "PREFERENCE_PASSWORD"
    const val PREFERENCE_ALIAS = "PREFERENCE_ALIAS"
    const val PREFERENCE_NEED_REGISTER = "PREFERENCE_NEED_REGISTER"
    const val PREFERENCE_TOKEN = "PREFERENCE_TOKEN"
    const val DATABASE_FALSE = 0
    const val DATABASE_TRUE = 1
}