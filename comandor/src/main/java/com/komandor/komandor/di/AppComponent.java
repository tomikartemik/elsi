package com.komandor.komandor.di;


import com.komandor.komandor.ui.auth.cert_list.CertListFragment;
import com.komandor.komandor.ui.auth.cert_validation.CertValidationFragment;
import com.komandor.komandor.ui.auth.registration.RegistrationFragment;
import com.komandor.komandor.ui.contact_info.ContactInfoFragment;
import com.komandor.komandor.ui.main.chats.ChatsFragment;
import com.komandor.komandor.ui.main.contacts.ContactsFragment;
import com.komandor.komandor.ui.main.profile.ProfileFragment;
import com.komandor.komandor.ui.chat.ChatFragment;
import com.komandor.komandor.ui.message_info.MessageInfoFragment;
import com.komandor.komandor.widgets.FileLoaderService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, ViewModelModule.class, StorageModule.class})
public interface AppComponent {
  
  void inject(CertListFragment injector);
  
  void inject(CertValidationFragment injector);
  
  void inject(RegistrationFragment injector);
  
  void inject(ProfileFragment injector);
  
  void inject(ContactsFragment injector);
  
  void inject(ChatsFragment injector);
  
  void inject(ContactInfoFragment injector);

  void inject(ChatFragment injector);

  void inject(MessageInfoFragment injector);
  
  void inject(FileLoaderService injector);
}
