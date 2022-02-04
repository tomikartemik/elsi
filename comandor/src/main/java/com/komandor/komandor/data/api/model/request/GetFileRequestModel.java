package com.komandor.komandor.data.api.model.request;

public class GetFileRequestModel {

  String token;

  String chat;

  String file;

  public GetFileRequestModel(String token, String chat, String file) {
    this.token = token;
    this.chat = chat;
    this.file = file;
  }
}
