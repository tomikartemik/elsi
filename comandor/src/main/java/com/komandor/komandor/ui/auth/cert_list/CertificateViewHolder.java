package com.komandor.komandor.ui.auth.cert_list;

import com.komandor.komandor.databinding.CertificateBinding;

import java.security.cert.X509Certificate;

import androidx.recyclerview.widget.RecyclerView;

public class CertificateViewHolder extends RecyclerView.ViewHolder {
  private CertificateBinding certificateBinding;
  
  public CertificateViewHolder(CertificateBinding binding) {
    super(binding.getRoot());
    certificateBinding = binding;
  }
  
  public void bind(String alias, X509Certificate certificate, CertListAdapter.OnCertificateItemClickListener onCertificateItemClickListener) {
    certificateBinding.setCertificate(new CertListItemViewModel(alias, certificate));
    certificateBinding.setOnItemClickListener(onCertificateItemClickListener);
    certificateBinding.executePendingBindings();
  }
}
