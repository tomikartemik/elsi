package com.komandor.komandor.ui.main.contacts;

import com.komandor.komandor.data.database.contacts.Contact;

public class ContactItemViewModel {
  private int id;
  private String upperString;
  private String lowerString;
  
  private boolean isKomandor = true;
  
  public ContactItemViewModel(Contact contact) {
    id = contact.getId();
    if (contact.getCompany() != null) {
      upperString = contact.getCompany();
      lowerString = contact.getSurname() + " " + contact.getName() + " " + contact.getPatronymic();
    } else if (contact.getName() != null) {
      upperString = contact.getSurname() + " " + contact.getName() + " " + contact.getPatronymic();
      lowerString = contact.getPhone();
    } else {
      isKomandor = false;
      upperString = contact.getContactName();
      lowerString = contact.getPhone();
    }
  }

  public String getLowerString() {
    return lowerString;
  }

  public String getUpperString() {
    return upperString;
  }

  public int getId() {
    return id;
  }
  
  public boolean isKomandor() {
    return isKomandor;
  }
}
