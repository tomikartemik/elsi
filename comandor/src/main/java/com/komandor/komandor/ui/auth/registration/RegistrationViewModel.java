package com.komandor.komandor.ui.auth.registration;

import com.komandor.komandor.App;
import com.komandor.komandor.R;
import com.komandor.komandor.common.BaseViewModel;
import com.komandor.komandor.data.api.KomandorAPI;
import com.komandor.komandor.data.temporary.TemporaryStorage;
import com.komandor.komandor.utils.Event;
import com.komandor.komandor.utils.KomandorException;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

public class RegistrationViewModel extends BaseViewModel {
  
  private final int TIME_TO_WAIT = 20;
  
  App app;
  KomandorAPI komandorAPI;
  TemporaryStorage temporaryStorage;
  RegistrationRepository registrationRepository;
  
  private Disposable timerDisposable;
  private int currentTime;

  private String phoneNumber = "";
  private String smsCode = "";
  
  private MutableLiveData<String> getCodeButtonText;
  private MutableLiveData<Event<Boolean>> navigateNext = new MutableLiveData<>();
  private MutableLiveData<Boolean> codeError = new MutableLiveData<>(false);
  private MutableLiveData<Boolean> isCodeSent = new MutableLiveData<>(false);
  private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
  private MutableLiveData<Boolean> isWait = new MutableLiveData<>(false);
  private MutableLiveData<Boolean> phoneError = new MutableLiveData<>(false);

  
  @Inject
  public RegistrationViewModel(RegistrationRepository registrationRepository, KomandorAPI komandorAPI, TemporaryStorage temporaryStorage, App app) {
    this.temporaryStorage = temporaryStorage;
    this.komandorAPI = komandorAPI;
    this.app = app;
    this.registrationRepository = registrationRepository;
    
    getCodeButtonText = new MutableLiveData<>(app.getResources().getString(R.string.get_code));
  }
  
  public void getCode() {
    if (!isValidPhone()) {
      phoneError.setValue(true);
      return;
    }
    
    if (timerDisposable == null || timerDisposable.isDisposed()) {
      isCodeSent.setValue(true);
      isWait.setValue(true);
      
      compositeDisposable.add(
          registrationRepository.registryPhone(phoneNumber)
              .subscribe(resource -> {
                switch (resource.status) {
                  case ERROR:
                    new KomandorException(resource.message).printStackTrace();
                    break;
                }
              })
      );
      
      startTimer();
    }
  }
  
  public void checkCode() {
    if(!isValidCode()) {
      codeError.setValue(true);
      return;
    }
    
    compositeDisposable.add(
        registrationRepository.confirmPhone(smsCode)
        .subscribe(resource -> {
          switch (resource.status) {
            case LOADING:
              isLoading.setValue(true);
              break;
            case SUCCESS:
              navigateNext.setValue(new Event<>(null));
              isLoading.setValue(false);
              break;
            case ERROR:
              isLoading.setValue(false);
              new KomandorException(resource.message).printStackTrace();
              break;
          }
        })
    );
  }
  
  private boolean isValidCode() {
    return !smsCode.isEmpty();
  }
  
  private Boolean isValidPhone() {
    return phoneNumber.length() == 18;
  }
  
  private void startTimer() {
    currentTime = TIME_TO_WAIT;
    String getCodeAgain = app.getResources().getString(R.string.get_code_again);
    timerDisposable = Observable.interval(0, 1, TimeUnit.SECONDS)
        .subscribe(tick -> {
          currentTime -= 1;
          if (currentTime == 0) {
            getCodeButtonText.postValue(getCodeAgain);
            isWait.postValue(false);
            timerDisposable.dispose();
            return;
          }
          
          getCodeButtonText.postValue(getCodeAgain + " через " + getTimeString(currentTime));
        });
  }
  
  @Override
  protected void onCleared() {
    super.onCleared();
    if (timerDisposable != null) {
      timerDisposable.dispose();
    }
  }
  
  private String getTimeString(int time) {
    return String.format(Locale.ENGLISH, "%02d:%02d", time / 60, time % 60);
  }

  public MutableLiveData<Boolean> getIsLoading() {
    return isLoading;
  }

  public MutableLiveData<String> getGetCodeButtonText() {
    return getCodeButtonText;
  }

  public MutableLiveData<Boolean> getIsWait() {
    return isWait;
  }

  public MutableLiveData<Event<Boolean>> getNavigateNext() {
    return navigateNext;
  }

  public MutableLiveData<Boolean> getPhoneError() {
    return phoneError;
  }
  
  public MutableLiveData<Boolean> getCodeError() {
    return codeError;
  }

  public MutableLiveData<Boolean> getIsCodeSent() {
    return isCodeSent;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }
  
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  public String getSmsCode() {
    return smsCode;
  }
  
  public void setSmsCode(String smsCode) {
    this.smsCode = smsCode;
  }
}
