package com.komandor.komandor.data.api.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponseModel {
  
  @SerializedName("pid")
  private Integer pid;
  
  @SerializedName("cid")
  private Integer cid;
  
  @SerializedName("type")
  private Integer type;
  
  @SerializedName("name")
  private String name;
  
  @SerializedName("company")
  private String company;
  
  @SerializedName("title")
  private String title;
  
  @SerializedName("inn")
  private String inn;
  
  @SerializedName("orgn")
  private String orgn;
  
  @SerializedName("snils")
  private String snils;
  
  @SerializedName("phone")
  private String phone;
  
  @SerializedName("email")
  private String email;
  
  @SerializedName("photo")
  private String photo;
  
  @SerializedName("certificates")
  private List<CertificateResponseModel> certificates = null;

//  @SerializedName("billing")
//  private Billing billing;
//
  @SerializedName("sync")
  private Boolean sync;
  
  
  public Integer getPid() {
    return pid;
  }
  
  public void setPid(Integer pid) {
    this.pid = pid;
  }
  
  public Integer getCid() {
    return cid;
  }
  
  public void setCid(Integer cid) {
    this.cid = cid;
  }
  
  public Integer getType() {
    return type;
  }
  
  public void setType(Integer type) {
    this.type = type;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
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
  
  public String getInn() {
    return inn;
  }
  
  public void setInn(String inn) {
    this.inn = inn;
  }
  
  public String getOrgn() {
    return orgn;
  }
  
  public void setOrgn(String orgn) {
    this.orgn = orgn;
  }
  
  public String getSnils() {
    return snils;
  }
  
  public void setSnils(String snils) {
    this.snils = snils;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getPhoto() {
    return photo;
  }
  
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  
  public Boolean getSync() {
    return sync;
  }
  
  public void setSync(Boolean sync) {
    this.sync = sync;
  }
  
  public List<CertificateResponseModel> getCertificates() {
    return certificates;
  }
  
  public void setCertificates(List<CertificateResponseModel> certificates) {
    this.certificates = certificates;
  }
}
