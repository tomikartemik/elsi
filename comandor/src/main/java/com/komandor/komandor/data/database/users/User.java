package com.komandor.komandor.data.database.users;

import com.komandor.komandor.data.api.model.response.UserResponseModel;
import com.komandor.komandor.utils.SystemUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = User.TABLE_NAME)
public class User {
  public static final String TABLE_NAME = "ACCOUNTS";
  
  @PrimaryKey
  @ColumnInfo(name = "user_id")
  private int pid;
  
  @ColumnInfo(name = "cert_id")
  private int cid;
  
  @ColumnInfo(name = "type")
  private int type;
  
  @ColumnInfo(name = "surname")
  private String surname;
  
  @ColumnInfo(name = "name")
  private String name;
  
  @ColumnInfo(name = "patronymic")
  private String patronymic;
  
  @ColumnInfo(name = "company")
  private String company;
  
  @ColumnInfo(name = "title")
  private String title;
  
  @ColumnInfo(name = "INN")
  private String inn;
  
  @ColumnInfo(name = "OGRN")
  private String ogrn;
  
  @ColumnInfo(name = "SNILS")
  private String snils;
  
  @ColumnInfo(name = "phone")
  private String phone;
  
  @ColumnInfo(name = "photo")
  private String photo;
  
  @ColumnInfo(name = "email")
  private String email;
  
  public User() {
  }
  
  @Ignore
  public User(UserResponseModel user) {
    this.pid = user.getPid();
    this.cid = user.getCid();
    this.type = user.getType();
    
    String[] parsedFIO = SystemUtils.parseFIO(user.getName());
    this.surname = parsedFIO[0];
    this.name = parsedFIO[1];
    this.patronymic = parsedFIO[2];
    
    this.company = user.getCompany();
    this.title = user.getTitle();
    this.inn = user.getInn();
    this.ogrn = user.getOrgn();
    this.snils = user.getSnils();
    this.phone = SystemUtils.maskPhone(user.getPhone());
    this.photo = user.getPhoto();
    this.email = user.getEmail();
  }
  
  
  // ----------------------------- //
  
  public int getPid() {
    return pid;
  }
  
  public void setPid(int pid) {
    this.pid = pid;
  }
  
  public int getCid() {
    return cid;
  }
  
  public void setCid(int cid) {
    this.cid = cid;
  }
  
  public int getType() {
    return type;
  }
  
  public void setType(int type) {
    this.type = type;
  }
  
  public String getSurname() {
    return surname;
  }
  
  public void setSurname(String surname) {
    this.surname = surname;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getPatronymic() {
    return patronymic;
  }
  
  public void setPatronymic(String patronymic) {
    this.patronymic = patronymic;
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
  
  public String getOgrn() {
    return ogrn;
  }
  
  public void setOgrn(String ogrn) {
    this.ogrn = ogrn;
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
  
  public String getPhoto() {
    return photo;
  }
  
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
}
