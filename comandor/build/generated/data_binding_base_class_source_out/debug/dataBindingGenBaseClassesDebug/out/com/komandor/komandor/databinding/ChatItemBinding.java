package com.komandor.komandor.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.komandor.komandor.ui.main.chats.ChatItemViewModel;
import com.komandor.komandor.ui.main.chats.ChatsAdapter;

public abstract class ChatItemBinding extends ViewDataBinding {
  @NonNull
  public final ImageView chatsFragmentAvatar;

  @NonNull
  public final TextView chatsListItemBadge;

  @NonNull
  public final TextView chatsListItemLastMessage;

  @NonNull
  public final TextView chatsListItemName;

  @NonNull
  public final LinearLayout constraintLayout;

  @Bindable
  protected ChatsAdapter.OnChatItemClickListener mOnItemClickListener;

  @Bindable
  protected ChatItemViewModel mChat;

  protected ChatItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageView chatsFragmentAvatar, TextView chatsListItemBadge,
      TextView chatsListItemLastMessage, TextView chatsListItemName,
      LinearLayout constraintLayout) {
    super(_bindingComponent, _root, _localFieldCount);
    this.chatsFragmentAvatar = chatsFragmentAvatar;
    this.chatsListItemBadge = chatsListItemBadge;
    this.chatsListItemLastMessage = chatsListItemLastMessage;
    this.chatsListItemName = chatsListItemName;
    this.constraintLayout = constraintLayout;
  }

  public abstract void setOnItemClickListener(@Nullable ChatsAdapter.OnChatItemClickListener onItemClickListener);

  @Nullable
  public ChatsAdapter.OnChatItemClickListener getOnItemClickListener() {
    return mOnItemClickListener;
  }

  public abstract void setChat(@Nullable ChatItemViewModel chat);

  @Nullable
  public ChatItemViewModel getChat() {
    return mChat;
  }

  @NonNull
  public static ChatItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ChatItemBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ChatItemBinding>inflate(inflater, com.komandor.komandor.R.layout.li_chat, root, attachToRoot, component);
  }

  @NonNull
  public static ChatItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ChatItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ChatItemBinding>inflate(inflater, com.komandor.komandor.R.layout.li_chat, null, false, component);
  }

  public static ChatItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ChatItemBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
    return (ChatItemBinding)bind(component, view, com.komandor.komandor.R.layout.li_chat);
  }
}
