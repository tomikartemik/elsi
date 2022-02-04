package com.komandor.komandor.ui.loading;

import android.content.Context;

import com.komandor.komandor.utils.CryptoUtils;
import com.komandor.komandor.utils.PermissionUtils;

import androidx.lifecycle.ViewModel;

public class LoadingViewModel extends ViewModel {
  
  private OnNavigateListener navigateListener;
  
  
  public LoadingViewModel(OnNavigateListener navigateListener) {
    this.navigateListener = navigateListener;
  }
  
  public void startApp(Context context) {
    Thread loadCertificateThread = new Thread() {
      @Override
      public void run() {
        try {
          super.run();
          
          CryptoUtils.initCryptoPro(context);
          
          CryptoUtils.initCryptoProContainers(context);
          
          PermissionUtils.hasInternetPermissions(context);
          
          if (CryptoUtils.hasUserContainers()) {
            CryptoUtils.importContainers();
            
            if (CryptoUtils.hasCertificates()) {
//              if (AndroidKeyStore.getInstance().hasKey()) {
//                recoverSession();
//                return;
//              }
  
              navigateListener.navigateToValidationScreen();
              return;
            }
          }
          
          navigateListener.navigateToStartScreen();
        } catch (Exception e) {
          e.printStackTrace();
//          Crashlytics.logException(e);
        }
      }
    };
  
    loadCertificateThread.start();
  }
  
  public interface OnNavigateListener {
    void navigateToValidationScreen();
    
    void navigateToStartScreen();
  
//    void navigateToAuthScreen();

//    void navigateToMainScreen();
  }
}
