package com.komandor.komandor.data.api.model.request;

public class GetMessagesRequestModel {
  
  String token;
  
  String chat;
  
//  int id;
//
//  String rel;
//
//  String type;
  
  public GetMessagesRequestModel(String token, String chat /*, int id, String rel, String type */) {
    this.token = token;
    this.chat = chat;
//    this.id = id;
//    this.rel = rel;
//    this.type = type;
  }
  
  public String getToken() {
    return token;
  }
  
  public void setToken(String token) {
    this.token = token;
  }
  
  public String getChat() {
    return chat;
  }
  
  public void setChat(String chat) {
    this.chat = chat;
  }
  
//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }
//
//  public String getRel() {
//    return rel;
//  }
//
//  public void setRel(String rel) {
//    this.rel = rel;
//  }
//
//  public String getType() {
//    return type;
//  }
//
//  public void setType(String type) {
//    this.type = type;
//  }
}
