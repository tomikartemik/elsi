package com.komandor.komandor.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.komandor.komandor.common.MultipleFragmentsActivity;
import com.komandor.komandor.ui.auth.cert_list.CertListFragment;
import com.komandor.komandor.ui.auth.cert_validation.CertValidationFragment;
import com.komandor.komandor.ui.auth.registration.RegistrationFragment;
import com.komandor.komandor.ui.main.MainActivity;

public class AuthActivity extends MultipleFragmentsActivity implements
    CertListFragment.OnChooseCertificateListener, CertValidationFragment.OnLoginListener, RegistrationFragment.OnRegistrationListener {
  
  //private AuthViewModel authViewModel;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    if (savedInstanceState == null) {
      setCurrentFragment(CertListFragment.newInstance());
    }
  }
  
  @Override
  public void onChooseCertificate()
  {
    setCurrentFragment
            (CertValidationFragment
                    .newInstance());
  }
  
  @Override
  public void onLogin(boolean needRegistration) {
    hideKeyboard();
    if (needRegistration) {
      setCurrentFragment(RegistrationFragment.newInstance());
    } else {
      navigateToMainScreen();
    }
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return true;
  }
  
  @Override
  public void onRegistration() {
    navigateToMainScreen();
  }
  
  private void navigateToMainScreen() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    finish();
  }
  
  private void hideKeyboard() {
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    View view = getCurrentFocus();
    if (imm != null && view != null) {
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
      view.clearFocus();
    }
  }
}
