package com.komandor.komandor.data.api.model.response;

import com.google.gson.annotations.SerializedName;

public class CertificateResponseModel {
  
  @SerializedName("id")
  int certID;
  
  @SerializedName("cert")
  String base64;
  
  public int getCertID() {
    return certID;
  }
  
  public void setCertID(int certID) {
    this.certID = certID;
  }
  
  public String getBase64() {
    return base64;
  }
  
  public void setBase64(String base64) {
    this.base64 = base64;
  }
}
