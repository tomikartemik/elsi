package com.komandor.komandor.ui.contact_info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komandor.komandor.App;
import com.komandor.komandor.databinding.ContactInfoBinding;
import com.komandor.komandor.ui.chat.ChatActivity;
import com.komandor.komandor.utils.Constants;
import com.komandor.komandor.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ContactInfoFragment extends Fragment {
  public static final String CONTACT_ID_KEY = "CONTACT_ID";
  
  @Inject
  ViewModelFactory viewModelFactory;
  ContactInfoViewModel contactInfoViewModel;
  
  OnContactLoadFinishedListener onContactLoadFinishedListener;
  
  
  public static ContactInfoFragment newInstance(int contactID) {
    
    Bundle args = new Bundle();
    args.putInt(CONTACT_ID_KEY, contactID);
    
    ContactInfoFragment fragment = new ContactInfoFragment();
    fragment.setArguments(args);
    return fragment;
  }
  
  @Override
  public void onAttach(@NonNull Context context) {
    App.getAppComponent().inject(this);
    super.onAttach(context);
    
    if(context instanceof OnContactLoadFinishedListener) {
      onContactLoadFinishedListener = (OnContactLoadFinishedListener) context;
    }
    
    contactInfoViewModel = ViewModelProviders.of(this, viewModelFactory).get(ContactInfoViewModel.class);
    Bundle bundle = getArguments();
    contactInfoViewModel.setContactID(bundle.getInt(CONTACT_ID_KEY));
    contactInfoViewModel.loadContactInfo();
    
    contactInfoViewModel.getLoadingFinished()
        .observe(this, event -> onContactLoadFinishedListener
                .onContactLoadFinished(event.getContentIfNotHandled()));

    contactInfoViewModel.getOpenChatEvent()
        .observe(this, event -> {
          if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            intent.putExtra(Constants.INTENT_EXTRA_CHAT_ID, event.getContentIfNotHandled());
            startActivity(intent);
          }
        });
  }
  
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ContactInfoBinding binding = ContactInfoBinding.inflate(inflater, container, false);
    binding.setVm(contactInfoViewModel);
    binding.setLifecycleOwner(this);
    return binding.getRoot();
  }
  
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
  
  public interface OnContactLoadFinishedListener {
    void onContactLoadFinished(String title);
  }
}
