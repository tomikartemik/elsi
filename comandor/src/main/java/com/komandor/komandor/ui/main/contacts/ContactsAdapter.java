package com.komandor.komandor.ui.main.contacts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.komandor.komandor.data.database.contacts.Contact;
import com.komandor.komandor.databinding.ContactBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ContactsAdapter extends ListAdapter<Contact, ContactHolder> {
  
  private final OnContactItemClickListener onContactItemClickListener;
  
  public ContactsAdapter(OnContactItemClickListener listener) {
    super(CALLBACK);
    onContactItemClickListener = listener;
  }
  
  @NonNull
  @Override
  public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    ContactBinding binding = ContactBinding.inflate(inflater, parent, false);
    return new ContactHolder(binding);
  }
  
  @Override
  public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
    Contact contact = getItem(position);
    if(contact != null) {
      holder.bind(contact, onContactItemClickListener);
    }
    
  }
  
  private static final DiffUtil.ItemCallback<Contact> CALLBACK = new DiffUtil.ItemCallback<Contact>() {
    @Override
    public boolean areItemsTheSame(Contact oldItem, Contact newItem) {
      return oldItem.getId() == newItem.getId();
    }
    
    @Override
    public boolean areContentsTheSame(Contact oldItem, Contact newItem) {
      return oldItem.equals(newItem);
    }
  };
  
  public interface OnContactItemClickListener {
    void onContactItemClick(int contactID);
  }
  
}
