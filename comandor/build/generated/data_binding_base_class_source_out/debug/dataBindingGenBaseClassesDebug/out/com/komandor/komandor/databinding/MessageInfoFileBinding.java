package com.komandor.komandor.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.komandor.komandor.ui.message_info.MessageInfoViewModel;

public abstract class MessageInfoFileBinding extends ViewDataBinding {
  @NonNull
  public final FrameLayout messageInfoScreenButtonsLayout;

  @NonNull
  public final ProgressBar progressBar;

  @Bindable
  protected MessageInfoViewModel mVm;

  protected MessageInfoFileBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, FrameLayout messageInfoScreenButtonsLayout, ProgressBar progressBar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.messageInfoScreenButtonsLayout = messageInfoScreenButtonsLayout;
    this.progressBar = progressBar;
  }

  public abstract void setVm(@Nullable MessageInfoViewModel vm);

  @Nullable
  public MessageInfoViewModel getVm() {
    return mVm;
  }

  @NonNull
  public static MessageInfoFileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static MessageInfoFileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<MessageInfoFileBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_message_info_file, root, attachToRoot, component);
  }

  @NonNull
  public static MessageInfoFileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static MessageInfoFileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<MessageInfoFileBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_message_info_file, null, false, component);
  }

  public static MessageInfoFileBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static MessageInfoFileBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (MessageInfoFileBinding)bind(component, view, com.komandor.komandor.R.layout.fr_message_info_file);
  }
}
