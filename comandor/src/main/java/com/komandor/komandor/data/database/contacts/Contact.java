package com.komandor.komandor.data.database.contacts;

import com.komandor.komandor.data.api.model.response.ContactResponseModel;
import com.komandor.komandor.utils.Constants;
import com.komandor.komandor.utils.SystemUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = Contact.TABLE_NAME, indices = {@Index(value = {"pid", "local_id"}, unique = true)})
public class Contact {
  
  public static final String TABLE_NAME = "CONTACTS";
  
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  int id;
  
  @ColumnInfo(name = "type")
  int type;
  
  @ColumnInfo(name = "pid")
  int pid;
  
  @ColumnInfo(name = "local_id")
  int localID;
  
  @ColumnInfo(name = "uid")
  int uid;
  
  @ColumnInfo(name = "contact_name")
  String contactName;
  
  @ColumnInfo(name = "phone")
  String phone;
  
  @ColumnInfo(name = "unmasked_phone")
  String unmaskedPhone;
  
  @ColumnInfo(name = "full_name")
  String fullName;
  
  @ColumnInfo(name = "surname")
  String surname;
  
  @ColumnInfo(name = "name")
  String name;
  
  @ColumnInfo(name = "patronymic")
  String patronymic;
  
  @ColumnInfo(name = "company")
  String company;
  
  @ColumnInfo(name = "title")
  String title;
  
  @ColumnInfo(name = "photo")
  String photo;
  
  @ColumnInfo(name = "chat_id")
  String chatID;
  
  @ColumnInfo(name = "is_updated")
  boolean isUpdated;
  
  
  public Contact() {
  }
  
  @Ignore
  public Contact(int localID, String contactName, String phone) {
    this.localID = localID;
    this.contactName = contactName;
    this.phone = phone;
    this.unmaskedPhone = SystemUtils.unmaskPhone(phone);
    this.isUpdated = true;
  }
  
  @Ignore
  public Contact(ContactResponseModel contact) {
    this.type = contact.getType();
    this.pid = contact.getPid();
    this.uid = contact.getUid();
    
    this.unmaskedPhone = contact.getPhone();
    this.phone = SystemUtils.maskPhone(unmaskedPhone);
    
    this.fullName = contact.getFio();
    
    String[] parsedFIO = SystemUtils.parseFIO(this.fullName);
    this.surname = parsedFIO[0];
    this.name = parsedFIO[1];
    this.patronymic = parsedFIO[2];
    
    this.photo = contact.getPhoto();
    this.chatID = contact.getChatId();
    this.isUpdated = true;
    
    if (type == Constants.LP) {
      this.company = contact.getCompany();
      this.title = contact.getTitle();
    }
  }
  
  public void setLocalData(Contact localContact) {
    this.id = localContact.getId();
    this.localID = localContact.getLocalID();
    this.contactName = localContact.getContactName();
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getType() {
    return type;
  }
  
  public void setType(int type) {
    this.type = type;
  }
  
  public int getPid() {
    return pid;
  }
  
  public void setPid(int pid) {
    this.pid = pid;
  }
  
  public int getLocalID() {
    return localID;
  }
  
  public void setLocalID(int localID) {
    this.localID = localID;
  }
  
  public int getUid() {
    return uid;
  }
  
  public void setUid(int uid) {
    this.uid = uid;
  }
  
  public String getContactName() {
    return contactName;
  }
  
  public void setContactName(String contactName) {
    this.contactName = contactName;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  public String getUnmaskedPhone() {
    return unmaskedPhone;
  }
  
  public void setUnmaskedPhone(String unmaskedPhone) {
    this.unmaskedPhone = unmaskedPhone;
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
  
  public String getPhoto() {
    return photo;
  }
  
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  
  public String getChatID() {
    return chatID;
  }
  
  public void setChatID(String chatID) {
    this.chatID = chatID;
  }
  
  public boolean isUpdated() {
    return isUpdated;
  }
  
  public void setUpdated(boolean updated) {
    isUpdated = updated;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
}
