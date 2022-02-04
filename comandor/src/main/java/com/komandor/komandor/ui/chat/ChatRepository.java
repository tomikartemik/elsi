package com.komandor.komandor.ui.chat;

import com.komandor.komandor.data.database.KomandorDatabase;
import com.komandor.komandor.data.api.KomandorAPI;
import com.komandor.komandor.data.api.SocketAPI;
import com.komandor.komandor.data.api.model.ResponseModel;
import com.komandor.komandor.data.api.model.request.GetMessagesRequestModel;
import com.komandor.komandor.data.database.chats.Chat;
import com.komandor.komandor.data.database.chats.ChatsStorage;
import com.komandor.komandor.data.database.files.File;
import com.komandor.komandor.data.database.files.FileStorage;
import com.komandor.komandor.data.database.messages.Message;
import com.komandor.komandor.data.database.messages.MessagesDataSource;
import com.komandor.komandor.data.database.messages.MessagesStorage;
import com.komandor.komandor.data.model.DecryptedSessionKey;
import com.komandor.komandor.data.model.EncryptedFile;
import com.komandor.komandor.data.model.EncryptedMessage;
import com.komandor.komandor.data.model.EncryptedSessionKey;
import com.komandor.komandor.data.temporary.CryptoStorage;
import com.komandor.komandor.data.temporary.TemporaryStorage;

import javax.inject.Inject;

import androidx.paging.PagedList;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.BackpressureStrategy;
import io.reactivex.schedulers.Schedulers;
import androidx.paging.RxPagedListBuilder;
import io.reactivex.disposables.Disposable;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ChatRepository {

  KomandorAPI komandorAPI;
  KomandorDatabase komandorDatabase;
  SocketAPI socketAPI;
  MessagesStorage messagesStorage;
  ChatsStorage chatsStorage;
  FileStorage fileStorage;
  TemporaryStorage temporaryStorage;
  CryptoStorage cryptoStorage;

  Disposable disposable;

  @Inject
  public ChatRepository(
      KomandorAPI komandorAPI,
      KomandorDatabase komandorDatabase,
      SocketAPI socketAPI,
      MessagesStorage messagesStorage,
      ChatsStorage chatsStorage,
      FileStorage fileStorage,
      TemporaryStorage temporaryStorage,
      CryptoStorage cryptoStorage
  ) {
    this.komandorAPI = komandorAPI;
    this.komandorDatabase = komandorDatabase;
    this.socketAPI = socketAPI;
    this.messagesStorage = messagesStorage;
    this.chatsStorage = chatsStorage;
    this.fileStorage = fileStorage;
    this.temporaryStorage = temporaryStorage;
    this.cryptoStorage = cryptoStorage;
  }

  public Flowable<PagedList<Message>> getMessages(Chat chat, int pageSize) {
    DecryptedSessionKey sessionKey = cryptoStorage
            .decryptSessionKey
                    (chatsStorage
                            .getChatSessionKey(chat));

    PagedList.Config config = new PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setEnablePlaceholders(false)
        .build();

    return new RxPagedListBuilder<>(new MessagesDataSource
            .Factory
            (chat.getChatID(),
                    sessionKey,
                    temporaryStorage.getUserID(),
                    messagesStorage,
                    cryptoStorage,
                    komandorDatabase),
            config)
        .buildFlowable(BackpressureStrategy.LATEST)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public Flowable<Boolean> justUpdateMessages(String chatID) {
    return komandorAPI.getMessages(new GetMessagesRequestModel(temporaryStorage.getToken(), chatID))
        .subscribeOn(Schedulers.io())
        .map(ResponseModel::getData)
        .flatMap(messages -> {
          messagesStorage.insertMany(messages);
          return Flowable.just(true);
        });

  }

  public void sendMessage(Chat chat, String messageText) {
    if (messageText.equals("")) {
      return;
    }

    disposable =
        Observable.fromCallable(() -> {
          EncryptedSessionKey sessionKey = chatsStorage.getChatSessionKey(chat);
          EncryptedMessage encryptedMessage = cryptoStorage.encryptMessage(messageText, sessionKey);
          Message message = new Message(chat.getChatID(), temporaryStorage.getUserID(), encryptedMessage);

          long localID = messagesStorage.insertLocalMessage(message);
          chatsStorage.setLastMessageID(message.getChatID(), localID);
          socketAPI.sendMessage(localID, message);
          return true;
        })
            .subscribeOn(Schedulers.io())
            .subscribe();
  }

  public void sendFile(Chat chat, String filePath) {
    disposable =
        Observable.fromCallable(() -> {
          EncryptedSessionKey sessionKey = chatsStorage.getChatSessionKey(chat);
          EncryptedFile encryptedFile = cryptoStorage.encryptFile(filePath, sessionKey);

          File file = new File(encryptedFile, filePath);
          long localFileID = fileStorage.insertFile(file);
          file.setId(localFileID);
          Message message = new Message(chat.getChatID(), temporaryStorage.getUserID(), localFileID, encryptedFile);

          long localID = messagesStorage.insertLocalMessage(message);
          chatsStorage.setLastMessageID(message.getChatID(), localID);
          socketAPI.sendMessageWithFile(chat.getChatID(), localID, localFileID,  encryptedFile);
          return true;
        })
            .subscribeOn(Schedulers.io())
            .subscribe();
  }
}
