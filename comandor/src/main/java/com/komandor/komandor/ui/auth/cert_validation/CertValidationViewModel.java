package com.komandor.komandor.ui.auth.cert_validation;

import com.komandor.komandor.common.BaseViewModel;
import com.komandor.komandor.data.temporary.CryptoStorage;
import com.komandor.komandor.utils.Event;
import com.komandor.komandor.utils.KomandorException;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;

public class CertValidationViewModel extends BaseViewModel {
  
  CertValidationRepository certValidationRepository;
  CryptoStorage cryptoStorage;
  
  private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
  private MutableLiveData<Boolean> passwordError = new MutableLiveData<>(false);
  private MutableLiveData<Event<Boolean>> navigateNext = new MutableLiveData<>();
  
//  private MutableLiveData<String> password = new MutableLiveData<>("qweqwe123");
  private MutableLiveData<String> password = new MutableLiveData<>("");
  
  @Inject
  public CertValidationViewModel(CertValidationRepository certValidationRepository, CryptoStorage cryptoStorage) {
    this.certValidationRepository = certValidationRepository;
    this.cryptoStorage = cryptoStorage;
  }
  
  public String getSelectedCertificateClient() {
    return cryptoStorage
            .getSelectedCertificateUserName();
  }
  
  public void validateCertificate() {
    isLoading.setValue(true);
    
    if (password.getValue() == null || !cryptoStorage.loadPrivateKey(password.getValue())) {
      passwordError.setValue(true);
      isLoading.setValue(false);
      return;
    }
    
    String cert = cryptoStorage.getEncodedSelectedCertificate();
    String sign = cryptoStorage.signSelectedCertificate();
    
    compositeDisposable.add(certValidationRepository.startAuthentication(cert, sign)
        .subscribe(resource -> {
          password.setValue("");
          
          switch (resource.status) {
            case LOADING:
              isLoading.setValue(true);
              break;
            case SUCCESS:
              isLoading.setValue(false);
              navigateNext.setValue(new Event<>(resource.data.needRegisterPhone()));
              break;
            case ERROR:
              new KomandorException(resource.message).printStackTrace();
              isLoading.setValue(false);
              break;
            
            
          }
        }));
  }

  public MutableLiveData<Boolean> getIsLoading() {
    return isLoading;
  }

  public MutableLiveData<Event<Boolean>> getNavigateNext() {
    return navigateNext;
  }

  public MutableLiveData<String> getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password.setValue(password);
  }

  public MutableLiveData<Boolean> getPasswordError() {
    return passwordError;
  }
}
