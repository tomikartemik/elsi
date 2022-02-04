package com.komandor.komandor.data.temporary;

public class TemporaryStorage {
  
  private String token;
  
  private int userID;
  
  public TemporaryStorage() {
  }
  
  public void clearStorage() {
    token = null;
    userID = -1;
  }
  
  public String getToken() {
    return token;
  }
  
  public void setToken(String token) {
    this.token = token;
  }
  
  public int getUserID() {
    return userID;
  }
  
  public void setUserID(int userID) {
    this.userID = userID;
  }
  
}
