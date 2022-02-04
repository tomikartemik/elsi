package com.komandor.komandor.ui.auth.cert_list;

import com.komandor.komandor.utils.CryptoUtils;

import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.ViewModel;

public class CertListViewModel extends ViewModel {
  
  private Map<String, X509Certificate> certificates;
  
  public CertListViewModel() {
    this.certificates = CryptoUtils.getLocalCertificates();
  }
  
  public Map<String, X509Certificate> getCertificates() {
    return certificates;
  }
}
