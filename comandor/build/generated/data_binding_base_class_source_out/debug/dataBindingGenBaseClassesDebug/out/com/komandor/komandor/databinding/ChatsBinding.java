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
import com.komandor.komandor.ui.main.chats.ChatsViewModel;

public abstract class ChatsBinding extends ViewDataBinding {
  @Bindable
  protected ChatsViewModel mVm;

  protected ChatsBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount) {
    super(_bindingComponent, _root, _localFieldCount);
  }

  public abstract void setVm(@Nullable ChatsViewModel vm);

  @Nullable
  public ChatsViewModel getVm() {
    return mVm;
  }

  @NonNull
  public static ChatsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ChatsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ChatsBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_chats, root, attachToRoot, component);
  }

  @NonNull
  public static ChatsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ChatsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ChatsBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_chats, null, false, component);
  }

  public static ChatsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ChatsBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
    return (ChatsBinding)bind(component, view, com.komandor.komandor.R.layout.fr_chats);
  }
}
