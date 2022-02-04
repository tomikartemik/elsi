package com.komandor.komandor.data.database.contacts;

import com.komandor.komandor.data.database.certificates.Certificate;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

@Dao
public abstract class ContactsCertificatesDao {
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insert(Contact contact);
  
  @Update(onConflict = OnConflictStrategy.REPLACE)
  public abstract void update(Contact contact);
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insertCertificates(List<Certificate> certificates);
  
  @Query("select * from contacts where pid = :pid")
  public abstract Contact getContactByPID(int pid);
  
  @Query("select * from contacts where unmasked_phone = :phone and pid = 0")
  public abstract Contact getContactByPhone(String phone);
  
  @Transaction
  public void updateContact(Contact dbContact, Contact contact) {
    contact.setLocalData(dbContact);
    update(contact);
  }
  
  @Transaction
  public void insertContact(Contact contact) {
    Contact dbContact = getContactByPID(contact.getPid());
    if(dbContact != null) {
      updateContact(dbContact, contact);
      return;
    }
    
    dbContact = getContactByPhone(contact.getUnmaskedPhone());
    if(dbContact != null) {
      updateContact(dbContact, contact);
      return;
    }
    
    insert(contact);
  }
  
  @Transaction
  public void insertContactAndCertificates(Contact contact, List<Certificate> certificates) {
    insertContact(contact);
    insertCertificates(certificates);
  }
}
