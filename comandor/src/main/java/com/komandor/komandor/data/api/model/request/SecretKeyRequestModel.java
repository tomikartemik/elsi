package com.komandor.komandor.data.api.model.request;

import com.komandor.komandor.utils.SystemUtils;

public class SecretKeyRequestModel {
  private int cid;
  private int pid;
  private String key;
  
  public SecretKeyRequestModel (int pid, int cid, String key){
    this.cid = cid;
    this.pid = pid;
    this.key = key;
  }
  
  public SecretKeyRequestModel (int pid, int cid, byte[] key){
    this.cid = cid;
    this.pid = pid;
    this.key = SystemUtils.encodeBase64(key);
  }
  
  public int getCid() {
    return cid;
  }
  
  public int getPid() {
    return pid;
  }
  
  public String getKey() {
    return key;
  }
}
