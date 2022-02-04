package com.komandor.komandor.data.model;

import javax.crypto.SecretKey;

public class DecryptedSessionKey {
  private SecretKey key;
  private byte[] iv;
  
  public DecryptedSessionKey(SecretKey key, byte[] iv) {
    this.key = key;
    this.iv = iv;
  }
  
  public SecretKey getKey() {
    return key;
  }
  
  public byte[] getIV() {
    return iv;
  }
}
