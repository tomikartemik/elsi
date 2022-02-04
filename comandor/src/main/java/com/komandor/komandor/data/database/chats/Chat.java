package com.komandor.komandor.data.database.chats;

import com.komandor.komandor.data.api.model.response.ContactResponseModel;
import com.komandor.komandor.data.api.model.response.SecretKeyResponseModel;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = Chat.TABLE_NAME, indices = {@Index(value = {"chat_id"}, unique = true)})
public class Chat {
  public static final String TABLE_NAME = "CHATS";
  
  @NonNull
  @PrimaryKey()
  @ColumnInfo(name = "chat_id")
  String chatID;
  
  @ColumnInfo(name = "type")
  int type;
  
  @ColumnInfo(name = "name")
  String name;
  
  @ColumnInfo(name = "last_message_id")
  int lastMessageID;
  
  @ColumnInfo(name = "key_id")
  int keyID;
  
  @ColumnInfo(name = "key_import")
  boolean keyImport;
  
  @ColumnInfo(name = "key_base64")
  String keyBase64;
  
  @ColumnInfo(name = "cert_base64")
  String certBase64;
  
  @ColumnInfo(name = "unread_messages")
  int unreadMessages;
  
  @ColumnInfo(name = "is_updated")
  boolean isUpdated;
  
  
  public Chat() {
  }
  
  @Ignore
  public Chat(ContactResponseModel contactResponseModel, int lastMessageID) {
    this.chatID = contactResponseModel.getChatId();
    this.type = contactResponseModel.getChatType();
    
    this.name = contactResponseModel.getChatName();
    if (this.name == null || this.name.equals("")) {
      if (contactResponseModel.getCompany() == null) {
        this.name = contactResponseModel.getFio();
      } else {
        this.name = contactResponseModel.getCompany();
      }
    }
    
    
    SecretKeyResponseModel key = contactResponseModel.getKey();
    this.keyID = key.getId();
    this.keyImport = key.getImport();
    this.keyBase64 = key.getKey();
    this.certBase64 = key.getCert();
    
    this.lastMessageID = Math.max(lastMessageID, 0);
    this.unreadMessages = 0;
    this.isUpdated = true;
  }
  
  public String getChatID() {
    return chatID;
  }
  
  public void setChatID(String chatID) {
    this.chatID = chatID;
  }
  
  public int getType() {
    return type;
  }
  
  public void setType(int type) {
    this.type = type;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getKeyID() {
    return keyID;
  }
  
  public void setKeyID(int keyID) {
    this.keyID = keyID;
  }
  
  public boolean isKeyImport() {
    return keyImport;
  }
  
  public void setKeyImport(boolean keyImport) {
    this.keyImport = keyImport;
  }
  
  public String getKeyBase64() {
    return keyBase64;
  }
  
  public void setKeyBase64(String keyBase64) {
    this.keyBase64 = keyBase64;
  }
  
  public String getCertBase64() {
    return certBase64;
  }
  
  public void setCertBase64(String certBase64) {
    this.certBase64 = certBase64;
  }
  
  public int getUnreadMessages() {
    return unreadMessages;
  }
  
  public void setUnreadMessages(int unreadMessages) {
    this.unreadMessages = unreadMessages;
  }
  
  public boolean isUpdated() {
    return isUpdated;
  }
  
  public void setUpdated(boolean updated) {
    isUpdated = updated;
  }
}
