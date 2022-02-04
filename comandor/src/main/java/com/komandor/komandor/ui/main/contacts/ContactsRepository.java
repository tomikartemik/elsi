package com.komandor.komandor.ui.main.contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.komandor.komandor.utils.NetworkBoundResource;
import com.komandor.komandor.data.api.KomandorAPI;
import com.komandor.komandor.data.api.model.ResponseModel;
import com.komandor.komandor.data.api.model.request.GetContactsRequestModel;
import com.komandor.komandor.data.api.model.response.ContactResponseModel;
import com.komandor.komandor.data.database.contacts.Contact;
import com.komandor.komandor.data.database.contacts.ContactsStorage;
import com.komandor.komandor.data.temporary.TemporaryStorage;
import com.komandor.komandor.utils.KomandorLog;
import com.komandor.komandor.utils.Resource;
import com.komandor.komandor.utils.SystemUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import androidx.annotation.NonNull;
import io.reactivex.BackpressureStrategy;


@Singleton
public class ContactsRepository {
public static final String LOG_TAG = "CONTACTS_LOG";


  ContactsStorage contactsStorage;
  Context applicationContext;
  TemporaryStorage temporaryStorage;
  KomandorAPI komandorAPI;
  
  @Inject
  public ContactsRepository(ContactsStorage contactsStorage, Context applicationContext, TemporaryStorage temporaryStorage, KomandorAPI komandorAPI) {
    this.contactsStorage = contactsStorage;
    this.applicationContext = applicationContext;
    this.temporaryStorage = temporaryStorage;
    this.komandorAPI = komandorAPI;
  }

  // TODO DELETE LOGS
  public Flowable<Resource<List<Contact>>> updateContacts() {
    return new NetworkBoundResource<List<Contact>, List<ContactResponseModel>>() {
      
      @Override
      protected void saveCallResult(@NonNull List<ContactResponseModel> responseContacts) {
        KomandorLog.log(LOG_TAG, "END GET CONTACTS FROM SERVER");
        KomandorLog.log(LOG_TAG, "SET CONTACTS OLD");
        contactsStorage.setContactsOld();
        KomandorLog.log(LOG_TAG, "END SET CONTACTS OLD");
        for (ContactResponseModel contact : responseContacts) {
          KomandorLog.log(LOG_TAG, "INSERT CONTACT: " + contact.getFio());
          contactsStorage.insertContactWithCertificates(contact);
          KomandorLog.log(LOG_TAG, "END INSERT CONTACT");
        }
        KomandorLog.log(LOG_TAG, "DELETE  OLD CONTACTS");
        contactsStorage.deleteOldContacts();
        KomandorLog.log(LOG_TAG, "END DELETE  OLD CONTACTS");
      }
      
      @Override
      protected boolean shouldFetch() {
        return true;
      }
      
      @NonNull
      @Override
      protected Flowable<List<Contact>> loadData() {
        return contactsStorage.getSortedContacts();
      }
      
      @NonNull
      @Override
      protected Flowable<ResponseModel<List<ContactResponseModel>>> createCall() {
        KomandorLog.log(LOG_TAG, "START LOADING CONTACTS");
        return loadLocalContacts()
            .flatMap(phones -> {
                KomandorLog.log(LOG_TAG, "GET CONTACTS FROM SERVER");
                return komandorAPI.getContacts(new GetContactsRequestModel(temporaryStorage.getToken(), phones.toArray(new String[0])));
            });
      }
    }.asFlowable();
  }
  
  private Flowable<List<String>> loadLocalContacts() {
    return Flowable.create(observableEmitter -> {
      KomandorLog.log(LOG_TAG, "SET CONTACTS OLD");
      contactsStorage.setLocalContactsOld();
      KomandorLog.log(LOG_TAG, "END SET CONTACTS OLD");

      KomandorLog.log(LOG_TAG, "GET CONTACTS FROM PHONE");
      ContentResolver cr = applicationContext.getContentResolver();
      Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
          null, null, null, null);
      KomandorLog.log(LOG_TAG, "CURSOR RECEIVED");
      
      List<String> phones = new ArrayList<>();
      
      if (cur != null) {
        KomandorLog.log(LOG_TAG, "CURSOR SIZE: " + cur.getCount());
        while (cur.moveToNext()) {
          String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
          
          String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
          String phone = null;
          
          Cursor phoneCursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
              ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
          
          if (phoneCursor != null && phoneCursor.moveToFirst()) {
            phone = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            KomandorLog.log(LOG_TAG, "RECEIVED PHONE: " + phone);
            phoneCursor.close();
          }
          
          if (phone != null) {
            phone = phone.replaceFirst("^8", "+7");
            
            Pattern pattern = Pattern.compile("^\\+7.*");
            Matcher matcher = pattern.matcher(phone);
            
            if (matcher.find()) {
              String unmaskedPhone = SystemUtils.unmaskPhone(phone);
              
              if (unmaskedPhone.length() == 11) {
                phones.add(unmaskedPhone);
                KomandorLog.log(LOG_TAG, "INSERT NAME: " + name);
                contactsStorage.insertLocal(Integer.parseInt(id), name, phone);
                KomandorLog.log(LOG_TAG, "END INSERT");
              }
            }
          }
        }
        cur.close();
      }

      KomandorLog.log(LOG_TAG, "DELETE OLD CONTACTS");
      contactsStorage.deleteOldContacts();
      KomandorLog.log(LOG_TAG, "END DELETE OLD CONTACTS");
      observableEmitter.onNext(phones);
      observableEmitter.onComplete();
    }, BackpressureStrategy.BUFFER);
  }
}
