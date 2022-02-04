package com.komandor.komandor.data.database.messages;

import com.komandor.komandor.common.SnapshotDataSource;
import com.komandor.komandor.data.database.KomandorDatabase;
import com.komandor.komandor.data.model.DecryptedSessionKey;
import com.komandor.komandor.data.temporary.CryptoStorage;

import java.util.List;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import androidx.room.InvalidationTracker;
import androidx.room.paging.LimitOffsetDataSource;

public class MessagesDataSource extends SnapshotDataSource<Message, Integer> {
  private final MessagesStorage messagesStorage;
  private final DecryptedSessionKey decryptedSessionKey;
  private final CryptoStorage cryptoStorage;
  private final String chatID;
  private final int userPID;
  
  private final InvalidationTracker.Observer mObserver;
  
  public MessagesDataSource(String chatID, DecryptedSessionKey sessionKey, int userPID, MessagesStorage messagesStorage, CryptoStorage cryptoStorage, KomandorDatabase db) {
    this.chatID = chatID;
    this.userPID = userPID;
    this.messagesStorage = messagesStorage;
    this.cryptoStorage = cryptoStorage;
    this.decryptedSessionKey = sessionKey;
  
    mObserver = new InvalidationTracker.Observer(Message.TABLE_NAME) {
      @Override
      public void onInvalidated(@NonNull Set<String> tables) {
        invalidate();
      }
    };
    db.getInvalidationTracker().addObserver(mObserver);
  }
  
  
  @Override
  protected List<Integer> loadKeys() {
    return messagesStorage.getAllKeys(chatID);
  }
  
  @Override
  protected List<Message> loadForIds(List<Integer> ids) {
    List<Message> messages = messagesStorage.getMessagesByIDs(ids);
    
    for (int i = 0; i < messages.size(); i += 1) {
      Message message = messages.get(i);
      String decryptedMessage = cryptoStorage.decryptMessage(message, decryptedSessionKey);
      message.setDecryptedData(decryptedMessage);
      
      int nextMessagePID = -1;
      
      if(i + 1 < messages.size()) {
        nextMessagePID = messages.get(i + 1).getPid();
      }
      
      int currentMessagePID = message.getPid();
      message.setBelongToUser(currentMessagePID == userPID);
      message.setGrouped(nextMessagePID == currentMessagePID);
    }
    
    return messages;
  }
  
  public static class Factory extends DataSource.Factory<Integer, Message> {
    private final MessagesStorage messagesStorage;
    private final DecryptedSessionKey decryptedSessionKey;
    private final CryptoStorage cryptoStorage;
    private final String chatID;
    private final int userPID;
    private final KomandorDatabase db;
    
    
    public Factory(String chatID, DecryptedSessionKey sessionKey, int userPID, MessagesStorage messagesStorage, CryptoStorage cryptoStorage, KomandorDatabase db) {
      this.chatID = chatID;
      this.userPID = userPID;
      this.messagesStorage = messagesStorage;
      this.cryptoStorage = cryptoStorage;
      this.decryptedSessionKey = sessionKey;
      this.db = db;
    }
    
    @NonNull
    @Override
    public DataSource<Integer, Message> create() {
      return new MessagesDataSource(chatID, decryptedSessionKey, userPID, messagesStorage, cryptoStorage, db);
    }
  }
}
