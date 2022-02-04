package com.komandor.komandor.data.database.contacts;

import com.komandor.komandor.data.api.model.response.CertificateResponseModel;
import com.komandor.komandor.data.api.model.response.ContactResponseModel;
import com.komandor.komandor.data.database.certificates.Certificate;
import com.komandor.komandor.data.temporary.TemporaryStorage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class ContactsStorage {
  
  ContactDao contactDao;
  ContactsCertificatesDao contactsCertificatesDao;
  
  @Inject
  public ContactsStorage(ContactDao contactDao, ContactsCertificatesDao contactsCertificatesDao) {
    this.contactDao = contactDao;
    this.contactsCertificatesDao = contactsCertificatesDao;
  }
  
  
  // Insert local
  public void insertLocal(int localID, String name, String phone) {
    contactDao.insertLocalContact(new Contact(localID, name, phone));
  }
  
  public void insertContactWithCertificates(ContactResponseModel contact) {
    List<Certificate> certificates = new ArrayList<>();
    for(CertificateResponseModel certificate : contact.getCertificates()) {
      certificates.add(new Certificate(contact.getPid(), certificate));
    }
    
    contactsCertificatesDao.insertContactAndCertificates(new Contact(contact), certificates);
  }
  
  public Flowable<List<Contact>> getContacts() {
    return contactDao.getContacts();
  }
  
  public Flowable<List<Contact>> getSortedContacts() {
    return contactDao.getSortedContacts();
  }
  
  public Flowable<List<Contact>> getContactsByChatID(String chatID) {
    return contactDao.getContactsByChatID(chatID);
  }
  
  public Contact getContactByPID(int pid) {
    return contactDao.getContactByPID(pid);
  }
  
  public Flowable<Contact> getContactByID(int id) {
    return contactDao.getContactByID(id);
  }
  
  public Contact getLocalContactByPhone(String phone) {
    return contactDao.getLocalContactByPhone(phone);
  }
  
  public void setLocalContactsOld() {
    contactDao.setLocalContactsOld();
  }
  
  public void setContactsOld() {
    contactDao.setContactsOld();
  }
  
  public void deleteOldContacts() {
    contactDao.deleteOldContacts();
  }
}
