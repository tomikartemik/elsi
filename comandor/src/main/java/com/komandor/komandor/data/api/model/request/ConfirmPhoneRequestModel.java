package com.komandor.komandor.data.api.model.request;

public class ConfirmPhoneRequestModel {
  private String token;
  private int id;
  private String code;
  
  public ConfirmPhoneRequestModel(String token, String id, String code) {
    this.token = token;
    this.id = Integer.parseInt(id);
    this.code = code;
  }
}
