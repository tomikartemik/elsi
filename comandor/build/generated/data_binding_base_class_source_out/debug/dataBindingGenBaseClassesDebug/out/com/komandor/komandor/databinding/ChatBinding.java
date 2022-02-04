package com.komandor.komandor.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.komandor.komandor.ui.chat.ChatViewModel;

public abstract class ChatBinding extends ViewDataBinding {
  @NonNull
  public final EditText etChatMessageInput;

  @NonNull
  public final RecyclerView rvChatMessagesList;

  @Bindable
  protected ChatViewModel mVm;

  protected ChatBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount,
      EditText etChatMessageInput, RecyclerView rvChatMessagesList) {
    super(_bindingComponent, _root, _localFieldCount);
    this.etChatMessageInput = etChatMessageInput;
    this.rvChatMessagesList = rvChatMessagesList;
  }

  public abstract void setVm(@Nullable ChatViewModel vm);

  @Nullable
  public ChatViewModel getVm() {
    return mVm;
  }

  @NonNull
  public static ChatBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ChatBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ChatBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_chat, root, attachToRoot, component);
  }

  @NonNull
  public static ChatBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ChatBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ChatBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_chat, null, false, component);
  }

  public static ChatBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ChatBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
    return (ChatBinding)bind(component, view, com.komandor.komandor.R.layout.fr_chat);
  }
}
