package com.komandor.komandor.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.button.MaterialButton;
import com.komandor.komandor.ui.contact_info.ContactInfoViewModel;

public abstract class ContactInfoBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout contactInfoScreenEmptyLayout;

  @NonNull
  public final MaterialButton contactInfoScreenWriteMessageButton;

  @NonNull
  public final ImageView ivContactInfoAvatar;

  @NonNull
  public final ImageView mockImageView;

  @Bindable
  protected ContactInfoViewModel mVm;

  protected ContactInfoBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, LinearLayout contactInfoScreenEmptyLayout,
      MaterialButton contactInfoScreenWriteMessageButton, ImageView ivContactInfoAvatar,
      ImageView mockImageView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.contactInfoScreenEmptyLayout = contactInfoScreenEmptyLayout;
    this.contactInfoScreenWriteMessageButton = contactInfoScreenWriteMessageButton;
    this.ivContactInfoAvatar = ivContactInfoAvatar;
    this.mockImageView = mockImageView;
  }

  public abstract void setVm(@Nullable ContactInfoViewModel vm);

  @Nullable
  public ContactInfoViewModel getVm() {
    return mVm;
  }

  @NonNull
  public static ContactInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ContactInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ContactInfoBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_contact_info, root, attachToRoot, component);
  }

  @NonNull
  public static ContactInfoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ContactInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ContactInfoBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_contact_info, null, false, component);
  }

  public static ContactInfoBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ContactInfoBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ContactInfoBinding)bind(component, view, com.komandor.komandor.R.layout.fr_contact_info);
  }
}
