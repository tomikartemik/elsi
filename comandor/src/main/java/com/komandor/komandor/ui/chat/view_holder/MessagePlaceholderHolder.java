package com.komandor.komandor.ui.chat.view_holder;

import android.view.View;

import com.komandor.komandor.data.database.messages.Message;
import com.komandor.komandor.databinding.SentMessageBinding;
import com.komandor.komandor.ui.chat.MessageItemViewModel;

import androidx.recyclerview.widget.RecyclerView;

public class MessagePlaceholderHolder extends RecyclerView.ViewHolder {
  
  private View view;
  
  public MessagePlaceholderHolder(View inflatedView) {
    super(inflatedView);
  }
}
