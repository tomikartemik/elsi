package com.komandor.komandor.ui.auth.cert_list;

import android.text.TextUtils;

import com.komandor.komandor.utils.CryptoUtils;

import java.security.cert.X509Certificate;

import androidx.lifecycle.ViewModel;

public class CertListItemViewModel extends ViewModel {
  
  private boolean isCompany;
  private String companyName;
  private String clientName;
  private String alias;
  
  public CertListItemViewModel(String alias, X509Certificate certificate) {
    this.clientName = CryptoUtils.getClientName(certificate);
    this.companyName = CryptoUtils.getCompanyName(certificate);
    this.isCompany = !TextUtils.isEmpty(companyName);
    this.alias = alias;
  }
  
  public boolean isCompany() {
    return isCompany;
  }
  
  public String getCompanyName() {
    return companyName;
  }
  
  public String getClientName() {
    return clientName;
  }
  
  public String getAlias() {
    return alias;
  }
}
