package com.komandor.komandor.ui.message_info;

import javax.inject.Inject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;

import com.komandor.komandor.App;
import com.komandor.komandor.databinding.MessageInfoDefaultBinding;
import com.komandor.komandor.databinding.MessageInfoFileBinding;
import com.komandor.komandor.viewmodel.ViewModelFactory;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;



public class MessageInfoFragment extends Fragment {
  public static final String MESSAGE_ID_KEY = "MESSAGE_ID_KEY";
  public static final String MESSAGE_HAS_FILE_KEY = "MESSAGE_HAS_FILE_KEY";
  
  @Inject
  ViewModelFactory viewModelFactory;
  MessageInfoViewModel messageInfoViewModel;
  
  public static MessageInfoFragment newInstance(long messageID, boolean hasFile) {
    
    Bundle args = new Bundle();
    args.putLong(MESSAGE_ID_KEY, messageID);
    args.putBoolean(MESSAGE_HAS_FILE_KEY, hasFile);
    
    MessageInfoFragment fragment = new MessageInfoFragment();
    fragment.setArguments(args);
    return fragment;
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    boolean hasFile = getArguments().getBoolean(MESSAGE_HAS_FILE_KEY);
    if (hasFile) {
      MessageInfoFileBinding messageInfoFileBinding = MessageInfoFileBinding.inflate(inflater, container, false);
      messageInfoFileBinding.setVm(messageInfoViewModel);
      messageInfoFileBinding.setLifecycleOwner(this);
      return messageInfoFileBinding.getRoot();
    } else {
      MessageInfoDefaultBinding messageInfoDefaultBinding = MessageInfoDefaultBinding.inflate(inflater, container, false);
      messageInfoDefaultBinding.setVm(messageInfoViewModel);
      messageInfoDefaultBinding.setLifecycleOwner(this);
      return messageInfoDefaultBinding.getRoot();
    }
  }
  
  @Override
  public void onAttach(Context context) {
    App.getAppComponent().inject(this);
    super.onAttach(context);
    
    messageInfoViewModel = ViewModelProviders.of(this, viewModelFactory).get(MessageInfoViewModel.class);
    messageInfoViewModel
            .setMessageID(getArguments()
                    .getLong(MESSAGE_ID_KEY));
    messageInfoViewModel.loadMessageInfo();
    messageInfoViewModel.setOnOpenFolderClickListener((folderPath) -> {
//      Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
//      Uri folder = Uri.parse(folderPath);
//      intent.putExtra("android.provider.extra.INITIAL_URI", folder);
//      startActivity(intent);
    });
    // TODO Убрать диалог
    messageInfoViewModel.getOnVerifyEvent().observe(this, (event) -> {
      boolean result = event.getContentIfNotHandled();
      VerifiedSignDialogFragment dialog = VerifiedSignDialogFragment
              .newInstance(result);
      dialog.setDialogListener(new VerifiedSignDialogFragment.VerifiedSignDialogListener() {
        @Override
        public void onDialogPositiveClick(DialogFragment dialog) {
        }
  
        @Override
        public void onDialogNegativeClick(DialogFragment dialog) {
        }
      });
      dialog.show(getFragmentManager(), VerifiedSignDialogFragment.TAG);
    });
  }
}
