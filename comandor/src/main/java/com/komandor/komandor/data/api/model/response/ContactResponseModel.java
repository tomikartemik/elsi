package com.komandor.komandor.data.api.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactResponseModel {
  
  @SerializedName("uid")
  private Integer uid;
  
  @SerializedName("pid")
  private Integer pid;
  
  @SerializedName("user")
  private String fio;
  
  @SerializedName("name")
  private String company;
  
  @SerializedName("title")
  private String title;
  
  @SerializedName("phone")
  private String phone;
  
  @SerializedName("photo")
  private String photo;
  
  @SerializedName("type")
  private Integer type;
  
  @SerializedName("certificates")
  private List<CertificateResponseModel> certificates = null;
  
  @SerializedName("chatId")
  private String chatId;
  
  @SerializedName("chatType")
  private Integer chatType;
  
  @SerializedName("chatName")
  private String chatName;
  
  @SerializedName("key")
  private SecretKeyResponseModel key;
  
  public Integer getUid() {
    return uid;
  }
  
  public void setUid(Integer uid) {
    this.uid = uid;
  }
  
  public Integer getPid() {
    return pid;
  }
  
  public void setPid(Integer pid) {
    this.pid = pid;
  }
  
  public String getFio() {
    return fio;
  }
  
  public void setFio(String fio) {
    this.fio = fio;
  }
  
  public String getCompany() {
    return company;
  }
  
  public void setCompany(String company) {
    this.company = company;
  }
  
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  public String getPhoto() {
    return photo;
  }
  
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  
  public Integer getType() {
    return type;
  }
  
  public void setType(Integer type) {
    this.type = type;
  }
  
  public List<CertificateResponseModel> getCertificates() {
    return certificates;
  }
  
  public void setCertificates(List<CertificateResponseModel> certificates) {
    this.certificates = certificates;
  }
  
  public String getChatId() {
    return chatId;
  }
  
  public void setChatId(String chatId) {
    this.chatId = chatId;
  }
  
  public Integer getChatType() {
    return chatType;
  }
  
  public void setChatType(Integer chatType) {
    this.chatType = chatType;
  }
  
  public String getChatName() {
    return chatName;
  }
  
  public void setChatName(String chatName) {
    this.chatName = chatName;
  }
  
  public SecretKeyResponseModel getKey() {
    return key;
  }
  
  public void setKey(SecretKeyResponseModel key) {
    this.key = key;
  }
  
  @Override
  public String toString() {
    return "Name: " + company + ", Phone: " + phone;
  }
}
