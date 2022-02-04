package com.komandor.komandor.data.model;

public class EncryptedFile {

  int cid;
  private String encryptedName;
  private String nameSign;
  private String encryptedData;
  private String cms;

  public EncryptedFile(int cid, String encryptedName, String nameSign, String encryptedData, String cms) {
    this.cid = cid;
    this.encryptedName = encryptedName;
    this.nameSign = nameSign;
    this.encryptedData = encryptedData;
    this.cms = cms;
  }

  public int getCid() {
    return cid;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }

  public String getEncryptedName() {
    return encryptedName;
  }

  public void setEncryptedName(String encryptedName) {
    this.encryptedName = encryptedName;
  }

  public String getNameSign() {
    return nameSign;
  }

  public void setNameSign(String nameSign) {
    this.nameSign = nameSign;
  }

  public String getEncryptedData() {
    return encryptedData;
  }

  public void setEncryptedData(String encryptedData) {
    this.encryptedData = encryptedData;
  }

  public String getCms() {
    return cms;
  }

  public void setCms(String cms) {
    this.cms = cms;
  }
}
