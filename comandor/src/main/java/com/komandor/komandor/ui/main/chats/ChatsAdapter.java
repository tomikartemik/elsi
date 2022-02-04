package com.komandor.komandor.ui.main.chats;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.komandor.komandor.data.database.chats.ChatWithLastMessage;
import com.komandor.komandor.databinding.ChatItemBinding;

import androidx.annotation.NonNull;

public class ChatsAdapter extends ListAdapter<ChatWithLastMessage, ChatItemHolder> {
  
  private final OnChatItemClickListener onChatItemClickListener;
  
  public ChatsAdapter(OnChatItemClickListener listener) {
    super(CALLBACK);
    onChatItemClickListener = listener;
  }
  
  @NonNull
  @Override
  public ChatItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    ChatItemBinding binding = ChatItemBinding.inflate(inflater, parent, false);
    return new ChatItemHolder(binding);
  }
  
  @Override
  public void onBindViewHolder(@NonNull ChatItemHolder holder, int position) {
    ChatWithLastMessage chat = getItem(position);
    if(chat != null) {
      holder.bind(chat, onChatItemClickListener);
    }
    
  }
  
  private static final DiffUtil.ItemCallback<ChatWithLastMessage> CALLBACK = new DiffUtil.ItemCallback<ChatWithLastMessage>() {
    @Override
    public boolean areItemsTheSame(ChatWithLastMessage oldItem, ChatWithLastMessage newItem) {
      return oldItem.getChat().getChatID() == newItem.getChat().getChatID();
    }
    
    @Override
    public boolean areContentsTheSame(ChatWithLastMessage oldItem, ChatWithLastMessage newItem) {
      return oldItem.equals(newItem);
    }
  };
  
  public interface OnChatItemClickListener {
    void onChatItemClick(String chatID);
  }
}
