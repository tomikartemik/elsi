package com.komandor.komandor.data.database.chats;

import com.komandor.komandor.data.api.model.response.ContactResponseModel;
import com.komandor.komandor.data.database.messages.MessagesStorage;
import com.komandor.komandor.data.model.EncryptedSessionKey;
import com.komandor.komandor.data.temporary.CryptoStorage;
import com.komandor.komandor.data.temporary.TemporaryStorage;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class ChatsStorage {
  
  ChatDao chatDao;
  MessagesStorage messagesStorage;
  CryptoStorage cryptoStorage;
  
  @Inject
  public ChatsStorage(ChatDao chatDao, MessagesStorage messagesStorage, CryptoStorage cryptoStorage) {
    this.chatDao = chatDao;
    this.messagesStorage = messagesStorage;
    this.cryptoStorage = cryptoStorage;
  }
  
  public void insert(Chat chat) {
    chatDao.insert(chat);
  }
  
  // TODO Можно оптимизировать создав доп ДАО
  public void insert(ContactResponseModel chat) {
    int lastMessageID = messagesStorage.getLastMessageID(chat.getChatId());
    chatDao.insert(new Chat(chat, lastMessageID));
  }
  
  public void insertNewChat(ContactResponseModel chat) {
    chatDao.insert(new Chat(chat, -1));
  }
  
  public void insertChats(List<ContactResponseModel> chats) {
    List<Chat> chatsModels = new ArrayList<>();
    for (ContactResponseModel chat : chats) {
      // TODO Можно вынести в транзакцию
      int lastMessageID = messagesStorage.getLastMessageID(chat.getChatId());
      chatsModels.add(new Chat(chat, lastMessageID));
    }
    
    chatDao.insertChats(chatsModels);
  }
  
  public Flowable<List<Chat>> getChats() {
    return chatDao.getChats();
  }
  
  public Flowable<List<ChatWithLastMessage>> getChatsWithLastMessage() {
    return chatDao.getChatsWithLastMessage();
  }
  
  public Flowable<ChatWithMessages> getChatWithMessages(String chatID) {
    return chatDao.getChatWithMessages(chatID);
  }
  
  public Flowable<Chat> getChatByID(String chatID) {
    return chatDao.getChatByID( chatID);
  }
  
  public void setLastMessageID(String chatID, long id) {
    chatDao.setLastMessageID(chatID, id);
  }
  
  public void setAllChatsOld() {
    chatDao.setAllChatsOld();
  }
  
  public void deleteOldChats() {
    chatDao.deleteOldChats();
  }
  
  public EncryptedSessionKey getChatSessionKey(Chat chat) {
    if (chat.isKeyImport()) {
      X509Certificate certificate = cryptoStorage.getSelectedCertificate();
      return new EncryptedSessionKey(chat.getKeyBase64(), certificate);
    }
    
    return new EncryptedSessionKey(chat.getKeyBase64(), chat.getCertBase64());
    
  }
  
  public void increaseUnreadMessages(String chatID) {
    chatDao.increaseUnreadMessages(chatID);
  }
  
}
