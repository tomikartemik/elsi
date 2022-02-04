package com.komandor.komandor.data.api.model.response;

import com.google.gson.annotations.SerializedName;

public class RegistryPhoneResponseModel {
  
  @SerializedName("id")
  private String codeID;
  
  public String getCodeID() {
    return codeID;
  }
  
  public void setCodeID(String codeID) {
    this.codeID = codeID;
  }
}
