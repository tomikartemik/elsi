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

public abstract class SentFileBinding extends ViewDataBinding {
  @Bindable
  protected MessageItemViewModel mMessage;

  @Bindable
  protected MessagesAdapter.OnMessageItemClickListener mOnMessageItemClickListener;

  @Bindable
  protected MessagesAdapter.OnDownloadFileClickListener mOnDownloadFileClickListener;

  protected SentFileBinding(DataBindingComponent _bindingComponent, View _root,
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

  public abstract void setOnDownloadFileClickListener(@Nullable MessagesAdapter.OnDownloadFileClickListener onDownloadFileClickListener);

  @Nullable
  public MessagesAdapter.OnDownloadFileClickListener getOnDownloadFileClickListener() {
    return mOnDownloadFileClickListener;
  }

  @NonNull
  public static SentFileBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static SentFileBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<SentFileBinding>inflate(inflater, com.komandor.komandor.R.layout.li_sent_file, root, attachToRoot, component);
  }

  @NonNull
  public static SentFileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static SentFileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<SentFileBinding>inflate(inflater, com.komandor.komandor.R.layout.li_sent_file, null, false, component);
  }

  public static SentFileBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static SentFileBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
    return (SentFileBinding)bind(component, view, com.komandor.komandor.R.layout.li_sent_file);
  }
}
