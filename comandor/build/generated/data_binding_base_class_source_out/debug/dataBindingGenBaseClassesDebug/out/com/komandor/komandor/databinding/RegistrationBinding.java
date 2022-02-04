package com.komandor.komandor.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.komandor.komandor.ui.auth.registration.RegistrationViewModel;

public abstract class RegistrationBinding extends ViewDataBinding {
  @NonNull
  public final MaterialButton btnAuthCheckCode;

  @NonNull
  public final MaterialButton btnAuthGetCode;

  @NonNull
  public final LinearLayout llRegistrationLoadingContainer;

  @NonNull
  public final ProgressBar pbRegistrationProgressBar;

  @NonNull
  public final TextInputEditText tietAuthCode;

  @NonNull
  public final TextInputEditText tietAuthPhone;

  @NonNull
  public final TextInputLayout tilAuthCode;

  @NonNull
  public final TextInputLayout tilAuthPhone;

  @Bindable
  protected RegistrationViewModel mVm;

  protected RegistrationBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, MaterialButton btnAuthCheckCode, MaterialButton btnAuthGetCode,
      LinearLayout llRegistrationLoadingContainer, ProgressBar pbRegistrationProgressBar,
      TextInputEditText tietAuthCode, TextInputEditText tietAuthPhone, TextInputLayout tilAuthCode,
      TextInputLayout tilAuthPhone) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnAuthCheckCode = btnAuthCheckCode;
    this.btnAuthGetCode = btnAuthGetCode;
    this.llRegistrationLoadingContainer = llRegistrationLoadingContainer;
    this.pbRegistrationProgressBar = pbRegistrationProgressBar;
    this.tietAuthCode = tietAuthCode;
    this.tietAuthPhone = tietAuthPhone;
    this.tilAuthCode = tilAuthCode;
    this.tilAuthPhone = tilAuthPhone;
  }

  public abstract void setVm(@Nullable RegistrationViewModel vm);

  @Nullable
  public RegistrationViewModel getVm() {
    return mVm;
  }

  @NonNull
  public static RegistrationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static RegistrationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<RegistrationBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_registration, root, attachToRoot, component);
  }

  @NonNull
  public static RegistrationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static RegistrationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<RegistrationBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_registration, null, false, component);
  }

  public static RegistrationBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static RegistrationBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (RegistrationBinding)bind(component, view, com.komandor.komandor.R.layout.fr_registration);
  }
}
