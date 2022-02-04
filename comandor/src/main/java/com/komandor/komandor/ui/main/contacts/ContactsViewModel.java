package com.komandor.komandor.ui.main.contacts;

import com.komandor.komandor.common.BaseViewModel;
import com.komandor.komandor.data.database.contacts.Contact;
import com.komandor.komandor.utils.KomandorException;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ContactsViewModel extends BaseViewModel {
  
  ContactsRepository contactsRepository;
  
  private String text;
  private MutableLiveData<List<Contact>> contacts = new MutableLiveData<>();
  private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
  private SwipeRefreshLayout.OnRefreshListener onRefreshListener = this::updateContacts;
  
  private ContactsAdapter.OnContactItemClickListener onContactItemClickListener;
  
  private MutableLiveData<Boolean> hasPermissions = new MutableLiveData<>(false);
  
  @Inject
  public ContactsViewModel(ContactsRepository contactsRepository) {
    this.contactsRepository = contactsRepository;
  }
  
  public void updateContacts() {
    singleDisposable = contactsRepository.updateContacts().subscribe(resource -> {
      switch (resource.status) {
        case LOADING:
          isLoading.setValue(true);
          break;
        case SUCCESS:
          isLoading.setValue(false);
          contacts.setValue(resource.data);
          break;
        case ERROR:
          isLoading.setValue(false);
          new KomandorException(resource.message).printStackTrace();
          break;
      }
    });
  }
  
  public String getText() {
    return text;
  }
  
  public void setText(String text) {
    this.text = text;
  }
  
  public LiveData<List<Contact>> getContacts() {
    return contacts;
  }
  
  public void setHasPermissions(boolean hasPermissions) {
    this.hasPermissions.setValue(hasPermissions);
  }
  
  public MutableLiveData<Boolean> getHasPermissions() {
    return hasPermissions;
  }
  
  public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
    return onRefreshListener;
  }
  
  public MutableLiveData<Boolean> getIsLoading() {
    return isLoading;
  }
  
  public ContactsAdapter.OnContactItemClickListener getOnContactItemClickListener() {
    return onContactItemClickListener;
  }
  
  public void setOnContactItemClickListener(ContactsAdapter.OnContactItemClickListener onContactItemClickListener) {
    this.onContactItemClickListener = onContactItemClickListener;
  }
}
