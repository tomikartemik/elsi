package com.komandor.komandor.data.database.messages;

import com.komandor.komandor.data.api.model.response.MessageResponseModel;
import com.komandor.komandor.data.model.EncryptedFile;
import com.komandor.komandor.data.model.EncryptedMessage;

import java.util.Random;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = Message.TABLE_NAME)
public class Message {
  public static final String TABLE_NAME = "MESSAGES";
  
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  long id;
  
  @ColumnInfo(name = "message_id")
  int messageID;
  
  @ColumnInfo(name = "chat_id")
  String chatID;
  
  @ColumnInfo(name = "pid")
  int pid;
  
  @ColumnInfo(name = "cid")
  int cid;
  
  @ColumnInfo(name = "user")
  String userName;
  
  @ColumnInfo(name = "data")
  String data;
  
  @ColumnInfo(name = "sign")
  String sign;
  
  @ColumnInfo(name = "type")
  String type;
  
  @ColumnInfo(name = "file_id")
  long fileID;
  
  @ColumnInfo(name = "date")
  long date;
  
  @Ignore
  String decryptedData;
  
  @Ignore
  boolean belongToUser = false;
  
  @Ignore
  boolean isGrouped = false;
  
  public Message() {
  }
  
  @Ignore
  public Message(MessageResponseModel messageResponseModel, long fileID) {
    if(messageResponseModel.getLocalID() != 0) {
      this.id = messageResponseModel.getLocalID();
    }
    this.messageID = messageResponseModel.getId();
    this.chatID = messageResponseModel.getChatID();
    this.pid = messageResponseModel.getPid();
    this.cid = messageResponseModel.getCid();
    this.userName = messageResponseModel.getUserName();
    this.data = messageResponseModel.getData();
    this.sign = messageResponseModel.getSign();
    this.type = messageResponseModel.getType();
    this.date = messageResponseModel.getTime() * 1000;
    
    this.fileID = fileID;
  }
  
  @Ignore
  public Message(MessageResponseModel messageResponseModel) {
    this(messageResponseModel, 0);
  }

  @Ignore
  public Message(String chatID, int pid, EncryptedMessage encryptedMessage) {
    this.chatID = chatID;
    this.pid = pid;
    this.date = System.currentTimeMillis();
    this.belongToUser = true;

    this.cid = encryptedMessage.getCid();
    this.data = encryptedMessage.getMessage();
    this.sign = encryptedMessage.getSign();
  }

  @Ignore
  public Message(String chatID, int pid, long fileID, EncryptedFile encryptedFile) {
    this.chatID = chatID;
    this.pid = pid;
    this.date = System.currentTimeMillis();
    this.belongToUser = true;

    this.fileID = fileID;
    this.cid = encryptedFile.getCid();
    this.data = encryptedFile.getEncryptedName();
    this.sign = encryptedFile.getNameSign();
  }
  
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public int getMessageID() {
    return messageID;
  }

  public void setMessageID(int messageID) {
    this.messageID = messageID;
  }
  
  public String getChatID() {
    return chatID;
  }
  
  public void setChatID(String chatID) {
    this.chatID = chatID;
  }
  
  public int getPid() {
    return pid;
  }
  
  public void setPid(int pid) {
    this.pid = pid;
  }
  
  public int getCid() {
    return cid;
  }
  
  public void setCid(int cid) {
    this.cid = cid;
  }
  
  public String getUserName() {
    return userName;
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public String getData() {
    return data;
  }
  
  public void setData(String data) {
    this.data = data;
  }
  
  public String getSign() {
    return sign;
  }
  
  public void setSign(String sign) {
    this.sign = sign;
  }
  
  public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public long getFileID() {
    return fileID;
  }
  
  public void setFileID(long fileID) {
    this.fileID = fileID;
  }
  
  public long getDate() {
    return date;
  }
  
  public void setDate(long date) {
    this.date = date;
  }
  
  public String getDecryptedData() {
    return decryptedData;
  }
  
  public void setDecryptedData(String decryptedData) {
    this.decryptedData = decryptedData;
  }
  
  public boolean isBelongToUser() {
    return belongToUser;
  }
  
  public void setBelongToUser(boolean belongToUser) {
    this.belongToUser = belongToUser;
  }
  
  public boolean isGrouped() {
    return isGrouped;
  }
  
  public void setGrouped(boolean grouped) {
    isGrouped = grouped;
  }
  
  public boolean hasFile() {
    return fileID > 0;
  }
}
