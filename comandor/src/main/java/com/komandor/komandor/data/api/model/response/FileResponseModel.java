package com.komandor.komandor.data.api.model.response;

import com.google.gson.annotations.SerializedName;

public class FileResponseModel {
  @SerializedName("content")
  String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
