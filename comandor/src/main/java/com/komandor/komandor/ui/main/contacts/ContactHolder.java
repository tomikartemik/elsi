package com.komandor.komandor.ui.main.contacts;

import com.komandor.komandor.data.database.contacts.Contact;
import com.komandor.komandor.databinding.ContactBinding;

import androidx.recyclerview.widget.RecyclerView;

public class ContactHolder extends RecyclerView.ViewHolder {
  
  private ContactBinding contactBinding;
  
  public ContactHolder(ContactBinding binding) {
    super(binding.getRoot());
    contactBinding = binding;
  }
  
  public void bind(Contact contact, ContactsAdapter.OnContactItemClickListener listener) {
  contactBinding.setContact(new ContactItemViewModel(contact));
  contactBinding.setOnItemClickListener(listener);
  contactBinding.executePendingBindings();
  }
}
