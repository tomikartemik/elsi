package com.komandor.komandor.data.api.model.response;

import com.google.gson.annotations.SerializedName;

public class AuthResponseModel {
  
  @SerializedName("nonce")
  private String nonce;
  
  @SerializedName("token")
  private String token;
  
  public String getNonce() {
    return nonce;
  }
  
  public void setNonce(String nonce) {
    this.nonce = nonce;
  }
  
  public String getToken() {
    return token;
  }
  
  public void setToken(String token) {
    this.token = token;
  }
}
