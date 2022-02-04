package com.komandor.komandor.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.komandor.komandor.ui.auth.cert_list.CertListAdapter;
import com.komandor.komandor.ui.auth.cert_list.CertListItemViewModel;

public abstract class CertificateBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout rootCertificateLi;

  @NonNull
  public final TextView tvCertLiLowerText;

  @NonNull
  public final TextView tvCertLiUpperText;

  @Bindable
  protected CertListItemViewModel mCertificate;

  @Bindable
  protected CertListAdapter.OnCertificateItemClickListener mOnItemClickListener;

  protected CertificateBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, LinearLayout rootCertificateLi, TextView tvCertLiLowerText,
      TextView tvCertLiUpperText) {
    super(_bindingComponent, _root, _localFieldCount);
    this.rootCertificateLi = rootCertificateLi;
    this.tvCertLiLowerText = tvCertLiLowerText;
    this.tvCertLiUpperText = tvCertLiUpperText;
  }

  public abstract void setCertificate(@Nullable CertListItemViewModel certificate);

  @Nullable
  public CertListItemViewModel getCertificate() {
    return mCertificate;
  }

  public abstract void setOnItemClickListener(@Nullable CertListAdapter.OnCertificateItemClickListener onItemClickListener);

  @Nullable
  public CertListAdapter.OnCertificateItemClickListener getOnItemClickListener() {
    return mOnItemClickListener;
  }

  @NonNull
  public static CertificateBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static CertificateBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<CertificateBinding>inflate(inflater, com.komandor.komandor.R.layout.li_certificate, root, attachToRoot, component);
  }

  @NonNull
  public static CertificateBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static CertificateBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<CertificateBinding>inflate(inflater, com.komandor.komandor.R.layout.li_certificate, null, false, component);
  }

  public static CertificateBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static CertificateBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (CertificateBinding)bind(component, view, com.komandor.komandor.R.layout.li_certificate);
  }
}
