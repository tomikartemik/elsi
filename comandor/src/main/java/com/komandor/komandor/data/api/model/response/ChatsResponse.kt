package com.komandor.komandor.data.api.model.response

import com.google.gson.annotations.SerializedName

data class ChatsResponse(
    @SerializedName("id")
    val id:String,
    @SerializedName("profileName")
    val profileName:String,

    @SerializedName("profileCompany")
    val profileCompany:String,

    @SerializedName("profileCompanyId")
    val profileCompanyId:String,

    @SerializedName("profileTitle")
    val profileTitle:String,

    @SerializedName("profilePhone")
    val profilePhone:String,

    @SerializedName("profilePhoto")
    val profilePhoto:String,

    @SerializedName("profileType")
    val profileType:Int,

    @SerializedName("profileVerified")
    val profileVerified:Boolean,

    @SerializedName("certificates")
    val certificates: List<CertificateResponse>,

    @SerializedName("chatType")
    val chatType: Int,
    @SerializedName("chatName")
    val chatName: String,
    @SerializedName("chatFavorites")
    val chatFavorites: Boolean,
    @SerializedName("chatKey")
    val chatKey: ChatKey?,
    @SerializedName("waitList")
    val waitList: List<CertificateResponse>,

    @SerializedName("lastMessageText")
    val lastMessageText: String,

    @SerializedName("lastMessageStatus")
    val lastMessageStatus: Int,

    @SerializedName("lastMessageTime")
    val lastMessageTime: String,

    @SerializedName("unreadMessages")
    val unreadMessages: Int,

    @SerializedName("status")
    val status: String,


    )