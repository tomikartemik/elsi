package com.komandor.komandor.data.temporary;

import com.komandor.komandor.data.api.model.request.SecretKeyRequestModel;
import com.komandor.komandor.data.database.certificates.Certificate;
import com.komandor.komandor.data.database.messages.Message;
import com.komandor.komandor.data.model.DecryptedSessionKey;
import com.komandor.komandor.data.model.EncryptedFile;
import com.komandor.komandor.data.model.EncryptedMessage;
import com.komandor.komandor.data.model.EncryptedSessionKey;
import com.komandor.komandor.utils.CryptoUtils;
import com.komandor.komandor.utils.KomandorException;
import com.komandor.komandor.utils.SystemUtils;

import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.List;

public class CryptoStorage {
  
  private String selectedAlias;
  
  private PrivateKey currentPrivateKey;
  
  private int userCID = -1;
  
  public CryptoStorage() {
  }
  
  public void clearStorage() {
    selectedAlias = null;
    currentPrivateKey = null;
    userCID = -1;
  }
  
  /*
  -----------------------------------------------------------------------------------------------
  Работа с данными
  -----------------------------------------------------------------------------------------------
  */
  
  public String sign(byte[] data) {
    return CryptoUtils.sign(currentPrivateKey, data);
  }
  
  public boolean verifySign(String data, String sign) {
    return verifySign(data, sign, getSelectedCertificate());
  }
  
  public boolean verifySign(String data, String sign, X509Certificate certificate) {
    return CryptoUtils.verifySign(data.getBytes(), SystemUtils.decodeBase64(sign), certificate);
  }
  
  public boolean verifyCMS(byte[] data, String cms) {
    return verifyCMS(data, cms, getSelectedCertificate());
  }
  
  public boolean verifyCMS(byte[] data, String cms, X509Certificate certificate) {
    return CryptoUtils.verifyCMS(data, SystemUtils.decodeBase64(cms), certificate);
  }
  
  public List<SecretKeyRequestModel> createSessionKeys(List<Certificate> certificates) throws KomandorException {
    return CryptoUtils.createSessionKeys(currentPrivateKey, certificates);
  }
  
  public DecryptedSessionKey decryptSessionKey(EncryptedSessionKey encryptedSessionKey) throws KomandorException {
    return CryptoUtils.decryptSessionKey(currentPrivateKey, encryptedSessionKey);
  }
  
  public EncryptedMessage encryptMessage(String message, EncryptedSessionKey encryptedSessionKey) {
    return CryptoUtils.encryptMessage(currentPrivateKey, userCID, message, encryptedSessionKey);
  }
  
  public String decryptMessage(Message encryptedMessage, EncryptedSessionKey encryptedSessionKey) {
    DecryptedSessionKey sessionKey = decryptSessionKey(encryptedSessionKey);
    return decryptMessage(encryptedMessage, sessionKey);
  }
  
  public String decryptMessage(Message encryptedMessage, DecryptedSessionKey decryptedSessionKey) {
    return CryptoUtils.decryptMessage(currentPrivateKey, encryptedMessage, decryptedSessionKey);
  }
  
  public EncryptedFile encryptFile(String filePath, EncryptedSessionKey encryptedSessionKey) {
    return CryptoUtils.encryptFile(filePath, userCID, getSelectedCertificate(), currentPrivateKey, encryptedSessionKey);
  }
  
  public byte[] decryptFile(String encryptedFileBase64, EncryptedSessionKey encryptedSessionKey) {
    DecryptedSessionKey decryptedSessionKey = decryptSessionKey(encryptedSessionKey);
    return CryptoUtils.decryptFile(encryptedFileBase64, currentPrivateKey, decryptedSessionKey);
  }
  
  public byte[] decryptFile(String encryptedFileBase64, DecryptedSessionKey decryptedSessionKey) {
    return CryptoUtils.decryptFile(encryptedFileBase64, currentPrivateKey, decryptedSessionKey);
  }
  
  public String decryptString(String message, EncryptedSessionKey encryptedSessionKey) {
    DecryptedSessionKey decryptedSessionKey = decryptSessionKey(encryptedSessionKey);
    return CryptoUtils.decryptString(currentPrivateKey, message, decryptedSessionKey);
  }
  
  public String decryptString(String message, DecryptedSessionKey decryptedSessionKey) {
    return CryptoUtils.decryptString(currentPrivateKey, message, decryptedSessionKey);
  }
  
  /*
  -----------------------------------------------------------------------------------------------
  Работа с сертификатами
  -----------------------------------------------------------------------------------------------
  */
  
  public X509Certificate getSelectedCertificate() {
    return CryptoUtils.getCertificateByAlias(selectedAlias);
  }
  
  public String getEncodedSelectedCertificate() {
    try {
      X509Certificate cert = getSelectedCertificate();
      return SystemUtils.encodeBase64(cert.getEncoded());
    } catch (Exception e) {
      e.printStackTrace();
//      Crashlytics.logException(e);
    }
    
    return null;
  }
  
  public String signSelectedCertificate() {
    try {
      return sign(getSelectedCertificate().getEncoded());
    } catch (CertificateEncodingException e) {
      e.printStackTrace();
      // Crashlytics.logException()
    }
    
    return null;
  }
  
  public String getSelectedCertificateUserName() {
    return CryptoUtils.getClientName(getSelectedCertificate());
  }
  
  /*
  -----------------------------------------------------------------------------------------------
     Работа с хранилищем ключей
  -----------------------------------------------------------------------------------------------
  */
  
  public boolean loadPrivateKey(String password) {
    currentPrivateKey = CryptoUtils.loadPrivateKey(selectedAlias, password);
    
    return currentPrivateKey != null;
  }
  
  /*
  -----------------------------------------------------------------------------------------------
     Getter'ы и Setter'ы
  -----------------------------------------------------------------------------------------------
  */
  
  public String getSelectedAlias() {
    return selectedAlias;
  }
  
  public void setSelectedAlias(String selectedAlias) {
    this.selectedAlias = selectedAlias;
  }
  
  public PrivateKey getCurrentPrivateKey() {
    return currentPrivateKey;
  }
  
  public void setCurrentPrivateKey(PrivateKey currentPrivateKey) {
    this.currentPrivateKey = currentPrivateKey;
  }
  
  public int getUserCID() {
    return userCID;
  }
  
  public void setUserCID(int userCID) {
    this.userCID = userCID;
  }
}
