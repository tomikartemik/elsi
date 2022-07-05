package com.komandor.komandor.data.database.model

import androidx.room.*
import com.komandor.komandor.data.api.model.response.CertificateResponse
import com.komandor.komandor.data.api.model.response.ChatKey

@Entity(tableName = "chat_table", indices = [Index(value = ["id"], unique = true)])
data class Chat (
    @PrimaryKey
    val id:String,
    val profileName:String,
    val profileCompany:String,
    val profileCompanyId:String,
    val profileTitle:String,
    val profilePhone:String,
    val profilePhoto:String,
    val profileType:Int,
    val profileVerified:Boolean,
    val certificates: List<CertificateResponse>,
    val chatType: Int, // "(number) Тип чата: 0 - системный (Командор), 1 - личный/избранное, 2 - группа, 3 - системная группа",
    val chatName: String,
    val chatFavorites: Boolean,
    val chatKey: ChatKey?,
    val waitList: List<CertificateResponse>,
    val lastMessageText: String,
    val lastMessageStatus: Int,
    val lastMessageTime: String,
    val unreadMessages: Int,
    val status: String,

    )