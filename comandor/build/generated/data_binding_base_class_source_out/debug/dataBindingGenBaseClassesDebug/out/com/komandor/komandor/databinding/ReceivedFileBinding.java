package com.komandor.komandor.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.komandor.komandor.ui.chat.MessageItemViewModel;
import com.komandor.komandor.ui.chat.MessagesAdapter;

public abstract class ReceivedFileBinding extends ViewDataBinding {
  @NonNull
  public final TextView messageItemTime;

  @Bindable
  protected MessageItemViewModel mMessage;

  @Bindable
  protected MessagesAdapter.OnMessageItemClickListener mOnMessageItemClickListener;

  @Bindable
  protected MessagesAdapter.OnDownloadFileClickListener mOnDownloadFileClickListener;

  protected ReceivedFileBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView messageItemTime) {
    super(_bindingComponent, _root, _localFieldCount);
    this.messageItemTime = messageItemTime;
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
  public static ReceivedFileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ReceivedFileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ReceivedFileBinding>inflate(inflater, com.komandor.komandor.R.layout.li_received_file, root, attachToRoot, component);
  }

  @NonNull
  public static ReceivedFileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ReceivedFileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ReceivedFileBinding>inflate(inflater, com.komandor.komandor.R.layout.li_received_file, null, false, component);
  }

  public static ReceivedFileBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ReceivedFileBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ReceivedFileBinding)bind(component, view, com.komandor.komandor.R.layout.li_received_file);
  }
}
