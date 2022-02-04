package com.komandor.komandor.data.model;

import com.komandor.komandor.utils.CryptoUtils;
import com.komandor.komandor.utils.KomandorException;

import java.security.cert.X509Certificate;

public class EncryptedSessionKey {
  X509Certificate certificate = null;
  String encryptedKey = null;
  
  public EncryptedSessionKey() {
  }
  
  public EncryptedSessionKey(String encryptedKey, String certificate){
    this.encryptedKey = encryptedKey;
    if(certificate != null) {
      this.certificate = CryptoUtils.decodeBase64Certificate(certificate);
    }
  }
  
  public EncryptedSessionKey(String encryptedKey, X509Certificate certificate){
    this.encryptedKey = encryptedKey;
    this.certificate = certificate;
  }
  
  public String getEncryptedKey() {
    return encryptedKey;
  }
  
  public X509Certificate getCertificate() {
    return certificate;
  }
  
  public boolean isValid() {
    return certificate != null && !encryptedKey.equals("") && encryptedKey != null;
  }
  
  public String getEncodedCertificate() {
    try {
      return CryptoUtils.encodeCertificate(certificate);
    } catch (KomandorException e) {
      e.printStackTrace();
      return null;
    }
  }
}
