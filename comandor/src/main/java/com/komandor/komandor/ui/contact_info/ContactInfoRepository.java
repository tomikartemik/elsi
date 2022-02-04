package com.komandor.komandor.ui.contact_info;

import com.komandor.komandor.data.api.KomandorAPI;
import com.komandor.komandor.data.api.SocketAPI;
import com.komandor.komandor.data.api.model.ResponseModel;
import com.komandor.komandor.data.api.model.request.CreateChatRequestModel;
import com.komandor.komandor.data.api.model.request.SecretKeyRequestModel;
import com.komandor.komandor.data.api.model.response.ContactResponseModel;
import com.komandor.komandor.data.database.certificates.CertificateDao;
import com.komandor.komandor.data.database.chats.Chat;
import com.komandor.komandor.data.database.chats.ChatsStorage;
import com.komandor.komandor.data.database.contacts.Contact;
import com.komandor.komandor.data.database.contacts.ContactDao;
import com.komandor.komandor.data.temporary.CryptoStorage;
import com.komandor.komandor.data.temporary.TemporaryStorage;
import com.komandor.komandor.utils.NetworkBoundResource;
import com.komandor.komandor.utils.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import io.reactivex.Flowable;

@Singleton
public class ContactInfoRepository {
  
  ContactDao contactDao;
  ChatsStorage chatsStorage;
  CertificateDao certificateDao;
  CryptoStorage cryptoStorage;
  KomandorAPI komandorAPI;
  TemporaryStorage temporaryStorage;
  SocketAPI socketAPI;
  
  @Inject
  public ContactInfoRepository(
      ContactDao contactDao,
      ChatsStorage chatsStorage,
      CertificateDao certificateDao,
      CryptoStorage cryptoStorage,
      KomandorAPI komandorAPI,
      TemporaryStorage temporaryStorage
  ) {
    this.contactDao = contactDao;
    this.chatsStorage = chatsStorage;
    this.certificateDao = certificateDao;
    this.cryptoStorage = cryptoStorage;
    this.komandorAPI = komandorAPI;
    this.temporaryStorage = temporaryStorage;
  }
  
  
  public Flowable<Resource<Contact>> loadContactInfo(int contactID) {
    return new NetworkBoundResource<Contact, Contact>() {
      @Override
      protected void saveCallResult(@NonNull Contact item) {
      }
      
      @Override
      protected boolean shouldFetch() {
        return false;
      }
      
      @NonNull
      @Override
      protected Flowable<Contact> loadData() {
        return contactDao
                .getContactByID(contactID);
      }
      
      @NonNull
      @Override
      protected Flowable<ResponseModel<Contact>> createCall() {
        return Flowable.empty();
      }
    }.asFlowable();
  }
  
  public Flowable<Resource<Chat>> createChat(int contactID) {
    return new NetworkBoundResource<Chat, ContactResponseModel>() {
      
      private String newChatID;
      
      @Override
      protected void saveCallResult(@NonNull ContactResponseModel chat) {
        newChatID = chat.getChatId();
        chatsStorage.insertNewChat(chat);
        socketAPI.subscribeChat(newChatID);
      }
      
      @Override
      protected boolean shouldFetch() {
        return true;
      }
      
      @NonNull
      @Override
      protected Flowable<Chat> loadData() {
        if (newChatID == null) {
          return Flowable.empty();
        }
        
        return chatsStorage.getChatByID(newChatID);
      }
      
      @NonNull
      @Override
      protected Flowable<ResponseModel<ContactResponseModel>> createCall() {
        return Flowable.zip(certificateDao
                        .getLocalCertificates(),
                certificateDao
                        .getCertificatesByContactID(contactID),
            (localCerts, contactCerts) -> {
              localCerts.addAll(contactCerts);
              return localCerts;
            })
            .flatMap(certificates -> {
              List<SecretKeyRequestModel> secretKeys = cryptoStorage.createSessionKeys(certificates);
              return komandorAPI.createChat(new CreateChatRequestModel(temporaryStorage.getToken(), "", 0, secretKeys));
            });
      }
    }.asFlowable();
  }
}
