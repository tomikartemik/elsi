package com.komandor.komandor.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.komandor.komandor.ui.chat.MessageItemViewModel;
import com.komandor.komandor.ui.chat.MessagesAdapter;

public abstract class SentMessageBinding extends ViewDataBinding {
  @Bindable
  protected MessageItemViewModel mMessage;

  @Bindable
  protected MessagesAdapter.OnMessageItemClickListener mOnMessageItemClickListener;

  protected SentMessageBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount) {
    super(_bindingComponent, _root, _localFieldCount);
  }

  public abstract void setMessage(@Nullable MessageItemViewModel message);

  @Nullable
  public MessageItemViewModel getMessage() {
    return mMessage;
  }

  public abstract void setOnMessageItemClickListener(@Nullable MessagesAdapter.OnMessageItemClickListener onMessageItemClickListener);

  @Nullable
  public MessagesAdapter.OnMessageItemClickListener getOnMessageItemClickListener() {
    return mOnMessageItemClickListener;
  }

  @NonNull
  public static SentMessageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static SentMessageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<SentMessageBinding>inflate(inflater, com.komandor.komandor.R.layout.li_sent_message, root, attachToRoot, component);
  }

  @NonNull
  public static SentMessageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static SentMessageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<SentMessageBinding>inflate(inflater, com.komandor.komandor.R.layout.li_sent_message, null, false, component);
  }

  public static SentMessageBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static SentMessageBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (SentMessageBinding)bind(component, view, com.komandor.komandor.R.layout.li_sent_message);
  }
}
