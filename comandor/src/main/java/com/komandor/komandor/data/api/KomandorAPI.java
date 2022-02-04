package com.komandor.komandor.data.api;

import com.komandor.komandor.data.api.model.request.CreateChatRequestModel;
import com.komandor.komandor.data.api.model.request.GetContactsRequestModel;
import com.komandor.komandor.data.api.model.request.GetFileRequestModel;
import com.komandor.komandor.data.api.model.request.GetMessagesRequestModel;
import com.komandor.komandor.data.api.model.response.AuthResponseModel;
import com.komandor.komandor.data.api.model.response.ConfirmAuthResponseModel;
import com.komandor.komandor.data.api.model.request.ConfirmPhoneRequestModel;
import com.komandor.komandor.data.api.model.response.ConfirmPhoneResponseModel;
import com.komandor.komandor.data.api.model.response.ContactResponseModel;
import com.komandor.komandor.data.api.model.request.GetUserRequesModel;
import com.komandor.komandor.data.api.model.response.FileResponseModel;
import com.komandor.komandor.data.api.model.response.MessageResponseModel;
import com.komandor.komandor.data.api.model.response.RegistryPhoneResponseModel;
import com.komandor.komandor.data.api.model.ResponseModel;
import com.komandor.komandor.data.api.model.response.UserResponseModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface KomandorAPI {
  
  @FormUrlEncoded
  @POST("api/auth/")
  Flowable<ResponseModel<AuthResponseModel>> authentication(@Field("cert") String cert, @Field("sign") String sign);

  @FormUrlEncoded
  @POST("api/confirmAuth/")
  Flowable<ResponseModel<ConfirmAuthResponseModel>> confirmAuthentication(@Field("token") String token, @Field("sign") String sign);

  @FormUrlEncoded
  @POST("api/registryPhone/")
  Flowable<ResponseModel<RegistryPhoneResponseModel>> registryPhone(@Field("token") String token, @Field("phone") String phone);

  @POST("api/confirmPhone/")
  Flowable<ResponseModel<ConfirmPhoneResponseModel>> confirmPhone(@Body ConfirmPhoneRequestModel confirmPhoneRequestModel);

  @POST("api/profile/")
  Flowable<ResponseModel<UserResponseModel>> getProfile(@Body GetUserRequesModel getUserRequesModel);

  @POST("api/contacts/")
  Flowable<ResponseModel<List<ContactResponseModel>>> getContacts(@Body GetContactsRequestModel getContactsModel);

  @POST("api/createChat/")
  Flowable<ResponseModel<ContactResponseModel>> createChat(@Body CreateChatRequestModel createChatModel);

  @POST("api/messages/")
  Flowable<ResponseModel<List<MessageResponseModel>>> getMessages(@Body GetMessagesRequestModel getMessagesModel);

  @POST("api/file/")
  Flowable<ResponseModel<String>> getFile(@Body GetFileRequestModel getFileRequestModel);
}
