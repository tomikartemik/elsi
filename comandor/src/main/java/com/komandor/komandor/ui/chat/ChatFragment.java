package com.komandor.komandor.ui.chat;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.komandor.komandor.App;
import com.komandor.komandor.R;
import com.komandor.komandor.databinding.ChatBinding;
import com.komandor.komandor.ui.message_info.MessageInfoActivity;
import com.komandor.komandor.ui.message_info.MessageInfoFragment;
import com.komandor.komandor.utils.Constants;
import com.komandor.komandor.utils.RealPathUtil;
import com.komandor.komandor.viewmodel.ViewModelFactory;
import com.komandor.komandor.widgets.FileLoaderService;
import com.komandor.komandor.widgets.MarginItemDecoration;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import static android.app.Activity.RESULT_OK;

public class ChatFragment extends Fragment {
  public static final String CHAT_ID_KEY = "CHAT_ID_KEY";

  @Inject
  ViewModelFactory viewModelFactory;
  ChatViewModel chatViewModel;

  public static ChatFragment newInstance(String chatID) {

    Bundle args = new Bundle();
    args.putString(CHAT_ID_KEY, chatID);

    ChatFragment fragment = new ChatFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ChatBinding binding = ChatBinding.inflate(inflater, container, false);
    binding.setVm(chatViewModel);
    binding.setLifecycleOwner(this);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    RecyclerView recyclerView = view.findViewById(R.id.rv_chat_messages_list);
    MarginItemDecoration marginItemDecoration = new MarginItemDecoration(8);
    recyclerView.addItemDecoration(marginItemDecoration);
  }

  @Override
  public void onAttach(@NonNull Context context) {
    App.getAppComponent().inject(this);
    super.onAttach(context);

    String chatID = getArguments().getString(CHAT_ID_KEY);
    chatViewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatViewModel.class);
//    chatViewModel.updateMessages(chatID);
    chatViewModel.initChat(chatID);
    chatViewModel.getLoadingFinishedEvent().observe(this, (event) -> {
      if (getActivity() != null) {
        getActivity().setTitle(event.getContentIfNotHandled());
      }
    });
    chatViewModel.setOnSendFileClickListener(v -> {
      Intent intent = new Intent()
          .setType("*/*")
          .setAction(Intent.ACTION_OPEN_DOCUMENT);

      startActivityForResult(Intent.createChooser(intent, "Select a file"), Constants.OPEN_FILE_RESULT);
    });
    chatViewModel.setOnMessageItemClickListener((id, hasFile) -> {
      Intent intent = new Intent(getActivity(), MessageInfoActivity.class);
      intent.putExtra(MessageInfoFragment.MESSAGE_ID_KEY, id);
      intent.putExtra(MessageInfoFragment.MESSAGE_HAS_FILE_KEY, hasFile);
      startActivity(intent);
    });
    chatViewModel.setOnDownloadFileClickListener((fileID) -> {
      Intent intent = new Intent(getActivity(), FileLoaderService.class);
      intent.putExtra(FileLoaderService.FILE_ID_KEY, fileID);
      intent.putExtra(FileLoaderService.CHAT_ID_KEY, chatID);
      getActivity().startService(intent);
    });

  }

  @Override
  public void onDetach() {
    super.onDetach();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == Constants.OPEN_FILE_RESULT && resultCode == RESULT_OK) {
      Uri uriFile = data.getData();

      chatViewModel.sendFile(RealPathUtil.getPath(getContext(), uriFile));
    }
  }

}
