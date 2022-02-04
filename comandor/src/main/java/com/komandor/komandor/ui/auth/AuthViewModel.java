package com.komandor.komandor.ui.auth;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

public class AuthViewModel extends ViewModel {
  
  private Fragment currentFragment;
  
  public void setCurrentFragment(Fragment fragment) {
    currentFragment = fragment;
  }
  
}
