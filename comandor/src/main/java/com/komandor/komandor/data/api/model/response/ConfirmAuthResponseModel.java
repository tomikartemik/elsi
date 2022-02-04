package com.komandor.komandor.data.api.model.response;

import com.google.gson.annotations.SerializedName;

public class ConfirmAuthResponseModel {
  
  @SerializedName("needRegisterPhone")
  boolean needRegisterPhone;
  
  public boolean needRegisterPhone() {
    return needRegisterPhone;
  }
  
  public void setNeedRegisterPhone(boolean needRegisterPhone) {
    this.needRegisterPhone = needRegisterPhone;
  }
  
}
