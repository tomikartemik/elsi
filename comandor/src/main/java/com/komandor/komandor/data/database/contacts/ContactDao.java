package com.komandor.komandor.data.database.contacts;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import io.reactivex.Flowable;

@Dao
public abstract class ContactDao {
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insert(Contact contact);
  
  @Update(onConflict = OnConflictStrategy.REPLACE)
  public abstract void update(Contact contact);
  
  @Query("select * from " + Contact.TABLE_NAME + " order by name asc")
  public abstract Flowable<List<Contact>> getContacts();
  
  @Query("select * from " +
      "(select * from (select * from " + Contact.TABLE_NAME + " where pid != 0 order by full_name asc) " +
      "union all " +
      "select * from (select * from " + Contact.TABLE_NAME + " where pid = 0 order by contact_name asc))")
  public abstract Flowable<List<Contact>> getSortedContacts();
  
  @Query("select * from " + Contact.TABLE_NAME + " where chat_id like :chatID")
  public abstract Flowable<List<Contact>> getContactsByChatID(String chatID);
  
  @Query("select * from " + Contact.TABLE_NAME + " where pid = :pid")
  public abstract Contact getContactByPID(int pid);
  
  @Query("select * from " + Contact.TABLE_NAME + " where id = :id")
  public abstract Flowable<Contact> getContactByID(int id);
  
  @Query("select * from " + Contact.TABLE_NAME + " where unmasked_phone = :phone and pid = 0")
  public abstract Contact getLocalContactByPhone(String phone);
  
  @Query("update " + Contact.TABLE_NAME + " set is_updated = 0 where pid = 0")
  public abstract void setLocalContactsOld();
  
  @Query("update " + Contact.TABLE_NAME + " set is_updated = 0 where pid != 0")
  public abstract void setContactsOld();
  
  @Query("delete from " + Contact.TABLE_NAME + " where is_updated = 0")
  public abstract void deleteOldContacts();
  
  @Transaction
  public void insertLocalContact(Contact contact) {
    Contact localContact = getLocalContactByPhone(contact.getUnmaskedPhone());
    if (localContact != null) {
      contact.setId(localContact.getId());
      update(contact);
      return;
    }
    
    insert(contact);
  }
  
}
