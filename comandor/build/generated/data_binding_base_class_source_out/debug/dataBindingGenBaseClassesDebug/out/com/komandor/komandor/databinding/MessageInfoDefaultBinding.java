package com.komandor.komandor.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.komandor.komandor.ui.message_info.MessageInfoViewModel;

public abstract class MessageInfoDefaultBinding extends ViewDataBinding {
  @NonNull
  public final FrameLayout messageInfoScreenButtonsLayout;

  @Bindable
  protected MessageInfoViewModel mVm;

  protected MessageInfoDefaultBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, FrameLayout messageInfoScreenButtonsLayout) {
    super(_bindingComponent, _root, _localFieldCount);
    this.messageInfoScreenButtonsLayout = messageInfoScreenButtonsLayout;
  }

  public abstract void setVm(@Nullable MessageInfoViewModel vm);

  @Nullable
  public MessageInfoViewModel getVm() {
    return mVm;
  }

  @NonNull
  public static MessageInfoDefaultBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static MessageInfoDefaultBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<MessageInfoDefaultBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_message_info_default, root, attachToRoot, component);
  }

  @NonNull
  public static MessageInfoDefaultBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static MessageInfoDefaultBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<MessageInfoDefaultBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_message_info_default, null, false, component);
  }

  public static MessageInfoDefaultBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static MessageInfoDefaultBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (MessageInfoDefaultBinding)bind(component, view, com.komandor.komandor.R.layout.fr_message_info_default);
  }
}
