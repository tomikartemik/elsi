package com.komandor.komandor.ui.main.chats;

import android.Manifest;
import android.view.LayoutInflater;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.komandor.komandor.App;
import com.komandor.komandor.databinding.ChatsBinding;
import com.komandor.komandor.ui.chat.ChatActivity;
import com.komandor.komandor.utils.Constants;
import com.komandor.komandor.utils.PermissionUtils;
import com.komandor.komandor.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ChatsFragment extends Fragment {
  
  public static final String TAG = "CHATS";
  
  @Inject
  ViewModelFactory viewModelFactory;
  ChatsViewModel chatsViewModel;
  
  private boolean onStartUpdated = false;
  
  public static ChatsFragment newInstance() {
    return new ChatsFragment();
  }
  
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ChatsBinding binding = ChatsBinding.inflate(inflater, container, false);
    binding.setVm(chatsViewModel);
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
    
    chatsViewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatsViewModel.class);
    chatsViewModel.setOnChatItemClickListener((chatID -> {
      if (getActivity() != null) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra(Constants.INTENT_EXTRA_CHAT_ID, chatID);
        startActivity(intent);
      }
    }));
  }
  
  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    
  }
  
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == Constants
            .CONTACTS_PERMISSIONS_RESULT && grantResults
            .length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      initData();
    }
  }
  
  public void checkPermissions() {
    if (onStartUpdated) return;
    
    if (!PermissionUtils.hasContactsPermissions(getContext())) {
      requestContactsPermissions();
      return;
    }
    
    initData();
    
  }
  
  private void initData() {
    chatsViewModel.setHasPermissions(true);
    chatsViewModel.updateChats();
    
    onStartUpdated = true;
  }
  
  private void requestContactsPermissions() {
    String[] permissions = {Manifest.permission.READ_CONTACTS};
    requestPermissions(permissions, Constants.CONTACTS_PERMISSIONS_RESULT);
  }
}
