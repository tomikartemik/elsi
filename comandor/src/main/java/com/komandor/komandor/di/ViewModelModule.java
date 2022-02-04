package com.komandor.komandor.di;

import com.komandor.komandor.ui.auth.cert_validation.CertValidationViewModel;
import com.komandor.komandor.ui.auth.registration.RegistrationViewModel;
import com.komandor.komandor.ui.chat.ChatViewModel;
import com.komandor.komandor.ui.contact_info.ContactInfoViewModel;
import com.komandor.komandor.ui.main.chats.ChatsViewModel;
import com.komandor.komandor.ui.main.contacts.ContactsViewModel;
import com.komandor.komandor.ui.main.profile.ProfileViewModel;
import com.komandor.komandor.ui.message_info.MessageInfoViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
  
  @Binds
  @IntoMap
  @ViewModelKey(CertValidationViewModel.class)
  abstract ViewModel bindCertValidationViewModel(CertValidationViewModel certValidationViewModel);
  
  @Binds
  @IntoMap
  @ViewModelKey(RegistrationViewModel.class)
  abstract ViewModel bindRegistrationViewModel(RegistrationViewModel registrationViewModel);
  
  @Binds
  @IntoMap
  @ViewModelKey(ContactsViewModel.class)
  abstract ViewModel bindContactsViewModel(ContactsViewModel contactsViewModel);
  
  @Binds
  @IntoMap
  @ViewModelKey(ProfileViewModel.class)
  abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);
  
  @Binds
  @IntoMap
  @ViewModelKey(ChatsViewModel.class)
  abstract ViewModel bindChatsViewModel(ChatsViewModel chatsViewModel);
  
  @Binds
  @IntoMap
  @ViewModelKey(ContactInfoViewModel.class)
  abstract ViewModel bindContactInfoViewModel(ContactInfoViewModel contactInfoViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(ChatViewModel.class)
  abstract ViewModel bindChatViewModel(ChatViewModel chatViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(MessageInfoViewModel.class)
  abstract ViewModel bindMessageInfoViewModel(MessageInfoViewModel messageInfoViewModel);
}
