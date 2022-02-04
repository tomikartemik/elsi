// Generated by Dagger (https://google.github.io/dagger).
package com.komandor.komandor.ui.main.contacts;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class ContactsViewModel_Factory implements Factory<ContactsViewModel> {
  private final Provider<ContactsRepository> contactsRepositoryProvider;

  public ContactsViewModel_Factory(Provider<ContactsRepository> contactsRepositoryProvider) {
    this.contactsRepositoryProvider = contactsRepositoryProvider;
  }

  @Override
  public ContactsViewModel get() {
    return new ContactsViewModel(contactsRepositoryProvider.get());
  }

  public static ContactsViewModel_Factory create(
      Provider<ContactsRepository> contactsRepositoryProvider) {
    return new ContactsViewModel_Factory(contactsRepositoryProvider);
  }

  public static ContactsViewModel newContactsViewModel(ContactsRepository contactsRepository) {
    return new ContactsViewModel(contactsRepository);
  }
}