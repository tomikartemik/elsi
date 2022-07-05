package com.komandor.komandor.data.mapper

import com.komandor.komandor.data.api.model.response.*
import com.komandor.komandor.data.database.model.Chat
import com.komandor.komandor.data.database.model.Message
import com.komandor.komandor.data.database.model.User
import com.komandor.komandor.utils.SystemUtils

/*
fun ChatsResponse.toСhatsKeysRequest(certificate:CertificateResponse, profileResponse: ProfileResponse): СhatsKeysRequest {
    return СhatsKeysRequest(
        chatId = this.id,
        cid = certificate.cid,
        pid = profileResponse.pid,

        key = this.chatKey?.key,
        sign =
    )
}

 */

fun ChatsResponse.toChat(): Chat {
    return Chat(
        id,
        profileName,
        profileCompany,
        profileCompanyId,
        profileTitle,
        profilePhone,
        profilePhoto,
        profileType,
        profileVerified,
        certificates,
        chatType,
        chatName,
        chatFavorites,
        chatKey,
        waitList,
        lastMessageText,
        lastMessageStatus,
        lastMessageTime,
        unreadMessages,
        status,
    )
}

fun MessageResponse.toMessage(): Message = Message(
    chatId,
    messageId,
    prevMessageId,
    lastMessageId,
    pid,
    cid,
    keyId,
    folderId,
    type,
    user,
    text,
    textSign,
    fileId,
    fileSize,
    fileSign,
    status,
    statusTime,
)

fun ProfileResponse.toUser(): User = User(
    pid = this.pid,
    cid = this.cid,
    type = this.type,
    name = this.name,
    company = this.company,
    title = this.title,
    inn = this.inn,
    orgn = this.orgn,
    snils = this.snils,
    phone = this.phone,
    email = this.email,
    photo = this.photo,
    certificates = this.certificates,
    billing = this.billing,
    tariffs = this.tariffs,
    sync = this.sync
)

