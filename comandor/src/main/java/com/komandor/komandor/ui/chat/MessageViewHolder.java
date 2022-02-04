package com.komandor.komandor.ui.chat;

import com.komandor.komandor.data.database.messages.Message;
import com.komandor.komandor.databinding.ReceivedFileBinding;
import com.komandor.komandor.databinding.ReceivedMessageBinding;
import com.komandor.komandor.databinding.SentFileBinding;
import com.komandor.komandor.databinding.SentMessageBinding;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class MessageViewHolder extends RecyclerView.ViewHolder {
  private ViewDataBinding binding;
  
  public MessageViewHolder(ViewDataBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }
  
  public void bind(Message message, MessagesAdapter.OnMessageItemClickListener listener) {
    if (binding instanceof ReceivedMessageBinding) {
      ReceivedMessageBinding receivedMessageBinding = ((ReceivedMessageBinding) binding);
      receivedMessageBinding.setMessage(new MessageItemViewModel(message));
      receivedMessageBinding.setOnMessageItemClickListener(listener);
    } else if (binding instanceof SentMessageBinding) {
      SentMessageBinding sentMessageBinding = ((SentMessageBinding) binding);
      sentMessageBinding.setMessage(new MessageItemViewModel(message));
      sentMessageBinding.setOnMessageItemClickListener(listener);
    }
    
    binding.executePendingBindings();
  }
  
  public void bind(Message message,
                   MessagesAdapter.OnMessageItemClickListener listener,
                   MessagesAdapter.OnDownloadFileClickListener onDownloadFileClickListener) {
     if (binding instanceof SentFileBinding) {
      SentFileBinding sentFileBinding = ((SentFileBinding) binding);
      sentFileBinding.setMessage(new MessageItemViewModel(message));
      sentFileBinding.setOnMessageItemClickListener(listener);
      sentFileBinding.setOnDownloadFileClickListener(onDownloadFileClickListener);
    }else if (binding instanceof ReceivedFileBinding) {
      ReceivedFileBinding receivedFileBinding = ((ReceivedFileBinding) binding);
      receivedFileBinding.setMessage(new MessageItemViewModel(message));
      receivedFileBinding.setOnMessageItemClickListener(listener);
      receivedFileBinding.setOnDownloadFileClickListener(onDownloadFileClickListener);
    }
    
    binding.executePendingBindings();
  }
}
