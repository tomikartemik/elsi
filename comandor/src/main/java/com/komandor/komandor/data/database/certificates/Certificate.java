package com.komandor.komandor.data.database.certificates;

import com.komandor.komandor.data.api.model.response.CertificateResponseModel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = Certificate.TABLE_NAME, indices = {@Index(value = {"cert_id"}, unique = true)})
public class Certificate {
  public static final String TABLE_NAME = "CERTIFICATES";
  
  @PrimaryKey()
  @ColumnInfo(name = "cert_id")
  int cid;
  
  @ColumnInfo(name = "contact_id")
  int pid;
  
  @ColumnInfo(name = "is_local")
  boolean isLocal;
  
  @ColumnInfo(name = "is_active")
  boolean isActive;
  
  @ColumnInfo(name = "base64")
  String base64;
  
  public Certificate() {
  }
  
  @Ignore
  public Certificate(int pid, CertificateResponseModel certificate, boolean isLocal, boolean isActive) {
    this.cid = certificate.getCertID();
    this.base64 = certificate.getBase64();
    
    this.isLocal = isLocal;
    this.isActive = isActive && isLocal;
    this.pid = pid;
  }
  
  @Ignore
  public Certificate(int pid, CertificateResponseModel certificate) {
    this.cid = certificate.getCertID();
    this.base64 = certificate.getBase64();
    
    this.isActive = false;
    this.isLocal = false;
    this.pid = pid;
  }
  
  public int getCid() {
    return cid;
  }
  
  public void setCid(int cid) {
    this.cid = cid;
  }
  
  public int getPid() {
    return pid;
  }
  
  public void setPid(int pid) {
    this.pid = pid;
  }
  
  public boolean isLocal() {
    return isLocal;
  }
  
  public void setLocal(boolean local) {
    isLocal = local;
  }
  
  public boolean isActive() {
    return isActive;
  }
  
  public void setActive(boolean active) {
    isActive = active;
  }
  
  public String getBase64() {
    return base64;
  }
  
  public void setBase64(String base64) {
    this.base64 = base64;
  }
}
