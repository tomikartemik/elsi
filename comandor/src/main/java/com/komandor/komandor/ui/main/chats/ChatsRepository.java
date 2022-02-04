package com.komandor.komandor.ui.main.chats;

import com.komandor.komandor.data.api.KomandorAPI;
import com.komandor.komandor.data.api.model.ResponseModel;
import com.komandor.komandor.data.api.model.request.GetContactsRequestModel;
import com.komandor.komandor.data.api.model.response.ContactResponseModel;
import com.komandor.komandor.data.database.chats.ChatWithLastMessage;
import com.komandor.komandor.data.database.chats.ChatsStorage;
import com.komandor.komandor.data.model.EncryptedSessionKey;
import com.komandor.komandor.data.temporary.CryptoStorage;
import com.komandor.komandor.data.temporary.TemporaryStorage;
import com.komandor.komandor.ui.chat.ChatRepository;
import com.komandor.komandor.utils.NetworkBoundResource;
import com.komandor.komandor.utils.Resource;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.Flowable;
import androidx.annotation.NonNull;
import io.reactivex.disposables.Disposable;

public class ChatsRepository {
  
  ChatsStorage chatsStorage;
  KomandorAPI komandorAPI;
  TemporaryStorage temporaryStorage;
  CryptoStorage cryptoStorage;
  ChatRepository chatRepository;
  
  Disposable saveDisposable;
  
  @Inject
  public ChatsRepository(ChatsStorage chatsStorage, KomandorAPI komandorAPI, TemporaryStorage temporaryStorage, CryptoStorage cryptoStorage, ChatRepository chatRepository) {
    this.chatsStorage = chatsStorage;
    this.komandorAPI = komandorAPI;
    this.temporaryStorage = temporaryStorage;
    this.cryptoStorage = cryptoStorage;
    this.chatRepository = chatRepository;
  }
  
  
  public Flowable<Resource<List<ChatWithLastMessage>>> updateChats() {
    return new NetworkBoundResource<List<ChatWithLastMessage>, List<ContactResponseModel>>() {
      @Override
      protected void saveCallResult(@NonNull List<ContactResponseModel> chats) {
        chatsStorage.insertChats(chats);
      }
      
      @Override
      protected boolean shouldFetch() {
        return true;
      }
      
      @NonNull
      @Override
      protected Flowable<List<ChatWithLastMessage>> loadData() {
        return chatsStorage.getChatsWithLastMessage()
            .flatMapSingle(chats -> Flowable.fromIterable(chats)
                .flatMap(chatWithLastMessage -> {
                  if (chatWithLastMessage.getMessage() != null) {
                    EncryptedSessionKey sessionKey = chatsStorage.getChatSessionKey(chatWithLastMessage.getChat());
                    
                    String decryptedMessage = cryptoStorage.decryptMessage(chatWithLastMessage.getMessage(), sessionKey);
                    chatWithLastMessage.getMessage().setDecryptedData(decryptedMessage);
                  }
                  return Flowable.just(chatWithLastMessage);
                })
                .toList()
            );
      }
      
      @NonNull
      @Override
      protected Flowable<ResponseModel<List<ContactResponseModel>>> createCall() {
        return komandorAPI.getContacts(new GetContactsRequestModel(temporaryStorage.getToken()))
            .flatMap(response ->
                Flowable.fromIterable(response.getData())
                    .map(ContactResponseModel::getChatId)
                    .flatMap(chatID -> chatRepository.justUpdateMessages(chatID))
                    .toList()
                    .flatMapPublisher(whatever -> Flowable.just(response))
            );
      }
    }.asFlowable();
  }
  
}
