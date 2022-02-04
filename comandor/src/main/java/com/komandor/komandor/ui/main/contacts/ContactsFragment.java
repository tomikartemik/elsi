package com.komandor.komandor.ui.main.contacts;

import android.Manifest;
import android.view.ViewGroup;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;


import com.komandor.komandor.App;
import com.komandor.komandor.databinding.ContactsBinding;
import com.komandor.komandor.ui.contact_info.ContactInfoActivity;
import com.komandor.komandor.utils.Constants;
import com.komandor.komandor.utils.PermissionUtils;
import com.komandor.komandor.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ContactsFragment extends Fragment {
  
  public static final String TAG = "CONTACTS";
  
  @Inject
  ViewModelFactory viewModelFactory;
  ContactsViewModel contactsViewModel;
  
  private boolean onStartUpdated = false;
  
  public static ContactsFragment newInstance() {
    return new ContactsFragment();
  }
  
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ContactsBinding binding = ContactsBinding.inflate(inflater, container, false);
    binding.setVm(contactsViewModel);
    binding.setLifecycleOwner(this);
    return binding.getRoot();
  }
  
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
  
  @Override
  public void onAttach(@NonNull Context context) {
    App.getAppComponent().inject(this);
    super.onAttach(context);
    
    contactsViewModel = ViewModelProviders.of(this, viewModelFactory).get(ContactsViewModel.class);
    contactsViewModel.setOnContactItemClickListener(contactID -> {
      if (getActivity() != null) {
        Intent intent = new Intent(getActivity(), ContactInfoActivity.class);
        intent.putExtra(Constants.INTENT_EXTRA_CONTACT_ID, contactID);
        startActivity(intent);
      }
    });
  }
  
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == Constants.CONTACTS_PERMISSIONS_RESULT && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      initData();
    }
  }
  
  public void checkPermissions() {
    if(onStartUpdated) return;
    
    if (!PermissionUtils.hasContactsPermissions(getContext())) {
      requestContactsPermissions();
      return;
    }
    
    initData();
  }
  
  private void initData() {
    contactsViewModel.setHasPermissions(true);
    contactsViewModel.updateContacts();
    
    onStartUpdated = true;
  }
  
  private void requestContactsPermissions() {
    String[] permissions = {Manifest.permission.READ_CONTACTS};
    requestPermissions(permissions, Constants.CONTACTS_PERMISSIONS_RESULT);
  }
}
