package com.komandor.komandor.data.api

import com.komandor.komandor.data.api.model.request.*
import com.komandor.komandor.data.api.model.response.*
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface KomandorAPI {
    @POST("api/v2/auth")
    fun authentication(@Body authRequest: AuthRequest): Flow<ResponseModel<AuthResponse>>

    @POST("api/v2/confirmAuth")
    fun confirmAuthentication(
        @Body confirmAuthRequestModel: ConfirmAuthRequest,
    ): Flow<ResponseModel<ConfirmAuthResponse>>

    @POST("api/v2/registryPhone")
    fun registryPhone(
        @Body regisrtryPhoneRequest: RegisrtryPhoneRequest
    ): Flow<ResponseModel<RegistryPhoneResponse>>

    @POST("api/v2/confirmPhone")
    fun confirmPhone(@Field("code") code: String): Flow<ResponseModel<ConfirmPhoneResponse>>

    @POST("api/v2/profile")
    fun profile(): Flow<ResponseModel<ProfileResponse>>

    @POST("api/v2/photo")
    fun updatePhoto( @Body updatePhotoRequest: UpdatePhotoRequest): Flow<ResponseModel<Object>>

    @POST("api/v2/certificateRegistration/")
    fun certificateRegistration(): Flow<ResponseModel<String>>

    @POST("api/v2/chats")
    fun chats(@Body chatsRequest:ChatsRequest): Flow<ResponseModel<List<ChatsResponse>>>

    @POST("api/v2/chatsKeys")
    fun chatsKeys(@Body chatsKeysRequest:List<СhatsKeysRequest>): Flow<ResponseModel<List<ChatKey>>>


    @POST("api/v2/registryFCM")
    fun registryFCM(@Body registryFCMRequestModel: RegistryFCMRequestModel): Flow<ResponseModel<Object>>

    @POST("api/v2/messages")
    fun getMessages(@Body messagesModel: MessagesRequestModel): Flow<ResponseModel<List<MessageResponse>>>


    @POST("api/createChat/")
    fun createChat(@Body createChatModel: CreateChatRequestModel): Flow<ResponseModel<ContactResponse>>

    @POST("api/file/")
    fun getFile(@Body getFileRequestModel: GetFileRequestModel): Flow<ResponseModel<String>>
}