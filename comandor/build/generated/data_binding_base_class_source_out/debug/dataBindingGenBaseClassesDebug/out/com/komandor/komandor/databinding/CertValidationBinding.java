package com.komandor.komandor.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.komandor.komandor.ui.auth.cert_validation.CertValidationViewModel;

public abstract class CertValidationBinding extends ViewDataBinding {
  @NonNull
  public final MaterialButton btnCertValidationLogin;

  @NonNull
  public final LinearLayout llRegistrationLoadingContainer;

  @NonNull
  public final ProgressBar pbRegistrationProgressBar;

  @NonNull
  public final TextInputEditText tietCertValidationPasswordInput;

  @NonNull
  public final TextInputLayout tilCertValidationPasswordLayout;

  @NonNull
  public final TextView tvCertValidationSelectedCertificate;

  @NonNull
  public final TextView tvCertValidationSelectedCertificateText;

  @Bindable
  protected CertValidationViewModel mVm;

  protected CertValidationBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, MaterialButton btnCertValidationLogin,
      LinearLayout llRegistrationLoadingContainer, ProgressBar pbRegistrationProgressBar,
      TextInputEditText tietCertValidationPasswordInput,
      TextInputLayout tilCertValidationPasswordLayout, TextView tvCertValidationSelectedCertificate,
      TextView tvCertValidationSelectedCertificateText) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnCertValidationLogin = btnCertValidationLogin;
    this.llRegistrationLoadingContainer = llRegistrationLoadingContainer;
    this.pbRegistrationProgressBar = pbRegistrationProgressBar;
    this.tietCertValidationPasswordInput = tietCertValidationPasswordInput;
    this.tilCertValidationPasswordLayout = tilCertValidationPasswordLayout;
    this.tvCertValidationSelectedCertificate = tvCertValidationSelectedCertificate;
    this.tvCertValidationSelectedCertificateText = tvCertValidationSelectedCertificateText;
  }

  public abstract void setVm(@Nullable CertValidationViewModel vm);

  @Nullable
  public CertValidationViewModel getVm() {
    return mVm;
  }

  @NonNull
  public static CertValidationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static CertValidationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<CertValidationBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_certificate_validation, root, attachToRoot, component);
  }

  @NonNull
  public static CertValidationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static CertValidationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<CertValidationBinding>inflate(inflater, com.komandor.komandor.R.layout.fr_certificate_validation, null, false, component);
  }

  public static CertValidationBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static CertValidationBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (CertValidationBinding)bind(component, view, com.komandor.komandor.R.layout.fr_certificate_validation);
  }
}
