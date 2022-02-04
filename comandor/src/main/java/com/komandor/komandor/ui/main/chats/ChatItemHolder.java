package com.komandor.komandor.ui.main.chats;

import com.komandor.komandor.data.database.chats.ChatWithLastMessage;
import com.komandor.komandor.databinding.ChatItemBinding;

import androidx.recyclerview.widget.RecyclerView;

public class ChatItemHolder extends RecyclerView.ViewHolder {
  private ChatItemBinding chatItemBinding;
  
  public ChatItemHolder(ChatItemBinding binding) {
    super(binding.getRoot());
    chatItemBinding = binding;
  }
  
  public void bind(ChatWithLastMessage chat, ChatsAdapter.OnChatItemClickListener listener) {
    chatItemBinding.setChat(new ChatItemViewModel(chat));
    chatItemBinding.setOnItemClickListener(listener);
    chatItemBinding.executePendingBindings();
  }
}
