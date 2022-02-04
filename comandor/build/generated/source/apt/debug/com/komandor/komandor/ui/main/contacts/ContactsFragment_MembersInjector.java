// Generated by Dagger (https://google.github.io/dagger).
package com.komandor.komandor.ui.main.contacts;

import com.komandor.komandor.viewmodel.ViewModelFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ContactsFragment_MembersInjector implements MembersInjector<ContactsFragment> {
  private final Provider<ViewModelFactory> viewModelFactoryProvider;

  public ContactsFragment_MembersInjector(Provider<ViewModelFactory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<ContactsFragment> create(
      Provider<ViewModelFactory> viewModelFactoryProvider) {
    return new ContactsFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(ContactsFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  public static void injectViewModelFactory(
      ContactsFragment instance, ViewModelFactory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
