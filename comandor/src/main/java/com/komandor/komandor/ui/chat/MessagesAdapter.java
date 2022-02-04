package com.komandor.komandor.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komandor.komandor.R;
import com.komandor.komandor.data.database.messages.Message;
import com.komandor.komandor.databinding.ReceivedFileBinding;
import com.komandor.komandor.databinding.ReceivedMessageBinding;
import com.komandor.komandor.databinding.SentFileBinding;
import com.komandor.komandor.databinding.SentMessageBinding;
import com.komandor.komandor.ui.chat.view_holder.MessagePlaceholderHolder;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MessagesAdapter extends PagedListAdapter<Message, RecyclerView.ViewHolder> {
  
  private static final int VIEW_TYPE_MESSAGE_PLACEHOLDER = 0;
  private static final int VIEW_TYPE_MESSAGE_SENT = 1;
  private static final int VIEW_TYPE_MESSAGE_RECEIVED = 3;
  
  private static final int VIEW_TYPE_FILE_SENT = 2;
  private static final int VIEW_TYPE_FILE_RECEIVED = 4;
  
  private final OnMessageItemClickListener onMessageItemClickListener;
  private final OnDownloadFileClickListener onDownloadFileClickListener;
  
  public MessagesAdapter(OnMessageItemClickListener onMessageItemClickListener, OnDownloadFileClickListener onDownloadFileClickListener) {
    super(CALLBACK);
    this.onMessageItemClickListener = onMessageItemClickListener;
    this.onDownloadFileClickListener = onDownloadFileClickListener;
  }
  
  @Override
  public int getItemViewType(int position) {
    Message message = getItem(position);
    if (message != null) {
      if (message.isBelongToUser()) {
        if (message.hasFile()) {
          return VIEW_TYPE_FILE_SENT;
        }
        
        return VIEW_TYPE_MESSAGE_SENT;
      } else {
        if (message.hasFile()) {
          return VIEW_TYPE_FILE_RECEIVED;
        }
        
        return VIEW_TYPE_MESSAGE_RECEIVED;
      }
    } else {
      return VIEW_TYPE_MESSAGE_PLACEHOLDER;
    }
  }
  
  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    ViewDataBinding binding;
    
    if (viewType == VIEW_TYPE_MESSAGE_SENT) {
      binding = SentMessageBinding.inflate(inflater, parent, false);
    } else if (viewType == VIEW_TYPE_FILE_SENT) {
      binding = SentFileBinding.inflate(inflater, parent, false);
    } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
      binding = ReceivedMessageBinding.inflate(inflater, parent, false);
    } else if (viewType == VIEW_TYPE_FILE_RECEIVED) {
      binding = ReceivedFileBinding.inflate(inflater, parent, false);
    } else {
      View view = inflater.inflate(R.layout.li_messages_placeholder, parent, false);
      return new MessagePlaceholderHolder(view);
    }
    
    return new MessageViewHolder(binding);
  }
  
  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    Message message = getItem(position);
    
    if (message != null) {
      if(getItemViewType(position) % 2 == 0) {
        ((MessageViewHolder) holder).bind(message, onMessageItemClickListener, onDownloadFileClickListener);
      } else {
        ((MessageViewHolder) holder).bind(message, onMessageItemClickListener);
      }
    }
  }
  
  private static final DiffUtil.ItemCallback<Message> CALLBACK = new DiffUtil.ItemCallback<Message>() {
    @Override
    public boolean areItemsTheSame(Message oldItem, Message newItem) {
      return oldItem.getId() == newItem.getId();
    }
    
    @Override
    public boolean areContentsTheSame(Message oldItem, Message newItem) {
      return oldItem.equals(newItem);
    }
  };
  
  public interface OnMessageItemClickListener {
    void onMessageItemClick(long messageID, boolean hasFile);
  }
  
  public interface OnDownloadFileClickListener {
    void onDownloadFileClick(long fileID);
  }
  
}
