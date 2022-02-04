package com.komandor.komandor.data.api.model.response;

import com.google.gson.annotations.SerializedName;

public class SecretKeyResponseModel {
  
  @SerializedName("id")
  private Integer id;
  
  @SerializedName("key")
  private String key;
  
  @SerializedName("import")
  private Boolean _import;
  
  @SerializedName("cert")
  private String cert;
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getKey() {
    return key;
  }
  
  public void setKey(String key) {
    this.key = key;
  }
  
  public Boolean getImport() {
    return _import;
  }
  
  public void setImport(Boolean _import) {
    this._import = _import;
  }
  
  public String getCert() {
    return cert;
  }
  
  public void setCert(String cert) {
    this.cert = cert;
  }
  
}
