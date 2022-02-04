package com.komandor.komandor.data.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseModel<T> {
  
  @SerializedName("success")
  @Expose
  private Boolean success;
  @SerializedName("error")
  @Expose
  private String error = "";
  
  @SerializedName("data")
  @Expose
  private T data;
  
  public Boolean isSuccessful() {
    return success;
  }
  
  public void setSuccess(Boolean success) {
    this.success = success;
  }
  
  public String getError() {
    return error;
  }
  
  public void setError(String error) {
    this.error = error;
  }
  
  public T getData() {
    return data;
  }
  
  public void setData(T data) {
    this.data = data;
  }
  
}
