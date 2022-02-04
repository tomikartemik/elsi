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
import com.komandor.komandor.ui.main.contacts.ContactItemViewModel;
import com.komandor.komandor.ui.main.contacts.ContactsAdapter;

public abstract class ContactBinding extends ViewDataBinding {
  @Bindable
  protected View mV;

  @Bindable
  protected ContactItemViewModel mContact;

  @Bindable
  protected ContactsAdapter.OnContactItemClickListener mOnItemClickListener;

  protected ContactBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount) {
    super(_bindingComponent, _root, _localFieldCount);
  }

  public abstract void setV(@Nullable View V);

  @Nullable
  public View getV() {
    return mV;
  }

  public abstract void setContact(@Nullable ContactItemViewModel contact);

  @Nullable
  public ContactItemViewModel getContact() {
    return mContact;
  }

  public abstract void setOnItemClickListener(@Nullable ContactsAdapter.OnContactItemClickListener onItemClickListener);

  @Nullable
  public ContactsAdapter.OnContactItemClickListener getOnItemClickListener() {
    return mOnItemClickListener;
  }

  @NonNull
  public static ContactBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ContactBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ContactBinding>inflate(inflater, com.komandor.komandor.R.layout.li_contact, root, attachToRoot, component);
  }

  @NonNull
  public static ContactBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ContactBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ContactBinding>inflate(inflater, com.komandor.komandor.R.layout.li_contact, null, false, component);
  }

  public static ContactBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ContactBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
    return (ContactBinding)bind(component, view, com.komandor.komandor.R.layout.li_contact);
  }
}
