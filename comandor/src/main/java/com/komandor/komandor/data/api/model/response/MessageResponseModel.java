package com.komandor.komandor.data.api.model.response;

import com.google.gson.annotations.SerializedName;

public class MessageResponseModel {
  
  @SerializedName("id")
  int id;
  
  @SerializedName("chat")
  String chatID;
  
  @SerializedName("pid")
  int pid;
  
  @SerializedName("cid")
  int cid;
  
  @SerializedName("user")
  String userName;
  
  @SerializedName("data")
  String data;
  
  @SerializedName("sign")
  String sign;
  
  @SerializedName("cms")
  String cms;
  
  @SerializedName("type")
  String type;
  
  @SerializedName("file")
  String fileID;
  
  @SerializedName("time")
  long time;
  
  @SerializedName("localId")
  int localID;
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
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
  
  public String getCms() {
    return cms;
  }
  
  public void setCms(String cms) {
    this.cms = cms;
  }
  
  public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public String getFileID() {
    return fileID;
  }
  
  public void setFileID(String fileID) {
    this.fileID = fileID;
  }
  
  public long getTime() {
    return time;
  }
  
  public void setTime(long time) {
    this.time = time;
  }
  
  public int getLocalID() {
    return localID;
  }
  
  public void setLocalID(int localID) {
    this.localID = localID;
  }
}
