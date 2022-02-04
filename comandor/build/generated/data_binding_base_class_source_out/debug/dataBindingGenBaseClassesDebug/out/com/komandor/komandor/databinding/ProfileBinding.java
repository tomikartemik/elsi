package com.komandor.komandor.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.button.MaterialButton;
import com.komandor.komandor.ui.main.profile.ProfileViewModel;

public abstract class ProfileBinding extends ViewDataBinding {
  @NonNull
  public final MaterialButton btnProfileLoadPhoto;

  @NonNull
  public final ImageView ivProfileAvatar;

  @NonNull
  public final TextView profileCompany;

  @NonNull
  public final ConstraintLayout profileCompanyLayout;

  @NonNull
  public final TextView profileCompanyText;

  @NonNull
  public final TextView profileEmail;

  @NonNull
  public final TextView profileEmailText;

  @NonNull
  public final TextView profileINN;

  @NonNull
  public final TextView profileINNText;

  @NonNull
  public final TextView profileLicense;

  @NonNull
  public final TextView profileLicenseText;

  @NonNull
  public final MaterialButton profileLogOutButton;

  @NonNull
  public final TextView profileName;

  @NonNull
  public final TextView profileNameText;

  @NonNull
  public final TextView profileOGRN;

  @NonNull
  public final ConstraintLayout profileOGRNLayout;

  @NonNull
  public final TextView profileOGRNText;

  @NonNull
  public final TextView profilePatronymic;

  @NonNull
  public final TextView profilePatronymicText;

  @NonNull
  public final TextView profilePhone;

  @NonNull
  public final TextView profilePhoneText;

  @NonNull
  public final TextView profileSNILS;

  @NonNull
  public final TextView profileSNILSText;

  @NonNull
  public final TextView profileSurname;

  @NonNull
  public final TextView profileSurnameText;

  @NonNull
  public final SwipeRefreshLayout rlProfileRefreshLayout;

  @Bindable
  protected ProfileViewModel mVm;

  protected ProfileBinding(DataBindingComponent _bindingComponent, View _root, int _localFieldCount,
      MaterialButton btnProfileLoadPhoto, ImageView ivProfileAvatar, TextView profileCompany,
      ConstraintLayout profileCompanyLayout, TextView profileCompanyText, TextView profileEmail,
      TextView profileEmailText, TextView profileINN, TextView profileINNText,
      TextView profileLicense, TextView profileLicenseText, MaterialButton profileLogOutButton,
      TextView profileName, TextView profileNameText, TextView profileOGRN,
      ConstraintLayout profileOGRNLayout, TextView profileOGRNText, TextView profilePatronymic,
      TextView profilePatronymicText, TextView profilePhone, TextView profilePhoneText,
      TextView profileSNILS, TextView profileSNILSText, TextView profileSurname,
      TextView profileSurnameText, SwipeRefreshLayout rlProfileRefreshLayout) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnProfileLoadPhoto = btnProfileLoadPhoto;
    this.ivProfileAvatar = ivProfileAvatar;
    this.profileCompany = profileCompany;
    this.profileCompanyLayout = profileCompanyLayout;
    this.profileCompanyText = profileCompanyText;
    this.profileEmail = profileEmail;
    this.profileEmailText = profileEmailText;
    this.profileINN = profileINN;
    this.profileINNText = profileINNText;
    this.profileLicense = profileLicense;
    this.profileLicenseText = profileLicenseText;
    this.profileLogOutButton = profileLogOutButton;
    this.profileName = profileName;
    this.profileNameText = profileNameText;
    this.profileOGRN = profileOGRN;
    this.profileOGRNLayout = profileOGRNLayout;
    this.profileOGRNText = profileOGRNText;
    this.profilePatronymic = profilePatronymic;
    this.profilePatronymicText = profilePatronymicText;
    this.profilePhone = profilePhone;
    this.profilePhoneText = profilePhoneText;
    this.profileSNILS = profileSNILS;
    this.profileSNILSText = profileSNILSText;
    this.profileSurname = profileSurname;
    this.profileSurnameText = profileSurnameText;
    this.rlProfileRefreshLayout = rlProfileRefreshLayout;
  }

  public abstract void setVm(@Nullable ProfileViewModel vm);

  @Nullable
  public ProfileViewModel getVm() {
    return mVm;
  }

  @NonNull
  public static ProfileBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ProfileBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ProfileBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_profile, root, attachToRoot, component);
  }

  @NonNull
  public static ProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ProfileBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_profile, null, false, component);
  }

  public static ProfileBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ProfileBinding bind(@NonNull View view, @Nullable DataBindingComponent component) {
    return (ProfileBinding)bind(component, view, com.komandor.komandor.R.layout.fr_profile);
  }
}
