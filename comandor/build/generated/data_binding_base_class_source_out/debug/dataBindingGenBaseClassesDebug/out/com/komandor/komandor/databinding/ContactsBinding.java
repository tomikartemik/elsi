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
import com.komandor.komandor.ui.main.contacts.ContactsViewModel;

public abstract class ContactsBinding extends ViewDataBinding {
  @Bindable
  protected ContactsViewModel mVm;

  protected ContactsBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount) {
    super(_bindingComponent, _root, _localFieldCount);
  }

  public abstract void setVm(@Nullable ContactsViewModel vm);

  @Nullable
  public ContactsViewModel getVm() {
    return mVm;
  }

  @NonNull
  public static ContactsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ContactsBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ContactsBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_contacts, root, attachToRoot, component);
  }

  @NonNull
  public static ContactsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ContactsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ContactsBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_contacts, null, false, component);
  }

  public static ContactsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ContactsBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
    return (ContactsBinding)bind(component, view, com.komandor.komandor.R.layout.fr_contacts);
  }
}
