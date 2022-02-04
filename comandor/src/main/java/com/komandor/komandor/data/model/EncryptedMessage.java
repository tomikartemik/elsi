package com.komandor.komandor.data.model;

public class EncryptedMessage {
  
  int cid;
  
  String message;
  
  String sign;
  
  public EncryptedMessage(int cid, String message, String sign) {
    this.cid = cid;
    this.message = message;
    this.sign = sign;
  }
  
  public int getCid() {
    return cid;
  }
  
  public void setCid(int cid) {
    this.cid = cid;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public String getSign() {
    return sign;
  }
  
  public void setSign(String sign) {
    this.sign = sign;
  }
}
