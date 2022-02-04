package com.komandor.komandor.data.api.model.request;

public class GetContactsRequestModel {
  
  String token;
  
  String[] phones = {};
  
  public GetContactsRequestModel(String token, String[] phones) {
    this.token = token;
    this.phones = phones;
  }
  
  public GetContactsRequestModel(String token) {
    this.token = token;
    this.phones = new String[]{};
  }
}
