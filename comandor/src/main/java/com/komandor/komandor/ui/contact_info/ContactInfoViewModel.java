package com.komandor.komandor.ui.contact_info;

import com.komandor.komandor.common.BaseViewModel;
import com.komandor.komandor.data.database.contacts.Contact;
import com.komandor.komandor.utils.Constants;
import com.komandor.komandor.utils.Event;
import com.komandor.komandor.utils.KomandorException;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;

public class ContactInfoViewModel extends BaseViewModel {
  
  ContactInfoRepository contactInfoRepository;
  
  private int contactID;
  private MutableLiveData<Contact> contact = new MutableLiveData<>();
  private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
  private MutableLiveData<Boolean> isWaiting = new MutableLiveData<>(false);
  private MutableLiveData<Event<String>> loadingFinished = new MutableLiveData<>();
  private MutableLiveData<Event<String>> openChatEvent = new MutableLiveData<>();
  
  @Inject
  public ContactInfoViewModel(ContactInfoRepository contactInfoRepository) {
    this.contactInfoRepository = contactInfoRepository;
  }
  
  public void loadContactInfo() {
    singleDisposable =
        contactInfoRepository.loadContactInfo(contactID)
            .subscribe(resource -> {
              switch (resource.status) {
                case LOADING:
                  isLoading.setValue(true);
                  break;
                case SUCCESS:
                  Contact loadedContact = resource.data;
                  contact.setValue(loadedContact);

                  int contactType = loadedContact.getType();
                  String screenTitle;
                  if (contactType == Constants.LP) {
                    screenTitle = loadedContact.getCompany();
                  } else if (contactType == Constants.NO_TYPE) {
                    screenTitle = loadedContact.getContactName();
                  } else {
                    screenTitle = loadedContact.getSurname() + " " + loadedContact.getName() + " " + loadedContact.getPatronymic();
                  }

                  loadingFinished.setValue(new Event<>(screenTitle));
                  isLoading.setValue(false);
                  break;
                case ERROR:
                  isLoading.setValue(false);
                  new KomandorException(resource.message).printStackTrace();
              }
            });
  }
  
  public void writeMessage() {
    Contact cntct = contact.getValue();
    String chatID = cntct.getChatID();
    
    if (chatID != null && !chatID.equals("")) {
      openChatEvent.setValue(new Event<>(chatID));
      return;
    }
    
    compositeDisposable.add(
        contactInfoRepository.createChat(cntct.getPid())
            .subscribe((resource) -> {
              switch (resource.status) {
                case LOADING:
                  isWaiting.setValue(true);
                  break;
                case SUCCESS:
                  isWaiting.setValue(false);
                  openChatEvent.setValue(new Event<>(resource.data.getChatID()));
                  break;
                case ERROR:
                  isWaiting.setValue(false);
                  throw new KomandorException(resource.message);
              }
            })
    );
  }
  
  public int getContactID() {
    return contactID;
  }
  
  public void setContactID(int contactID) {
    this.contactID = contactID;
  }
  
  public MutableLiveData<Contact> getContact() {
    return contact;
  }
  
  public MutableLiveData<Boolean> getIsLoading() {
    return isLoading;
  }
  
  public MutableLiveData<Event<String>> getLoadingFinished() {
    return loadingFinished;
  }
  
  public MutableLiveData<Boolean> getIsWaiting() {
    return isWaiting;
  }
  
  public MutableLiveData<Event<String>> getOpenChatEvent() {
    return openChatEvent;
  }
}
