package com.komandor.komandor.data.api.model.request;

import java.util.List;

public class CreateChatRequestModel {
  private String token;
  private int type;
  private String name;
  private SecretKeyRequestModel[] keys;
  
  public CreateChatRequestModel(String token, String name, int type, List<SecretKeyRequestModel> keys) {
    this.token = token;
    this.type = type;
    this.name = name;
    SecretKeyRequestModel[] arrayKeys = {};
    this.keys = keys.toArray(arrayKeys);
  }
}
