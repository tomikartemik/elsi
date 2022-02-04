package com.komandor.komandor.common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class MultipleFragmentsActivity extends SingleFragmentActivity {
  Fragment currentFragment;
  
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  
    if(savedInstanceState != null) {
      int lastFragmentIndex = getSupportFragmentManager().getBackStackEntryCount() - 1;
      if(lastFragmentIndex > 0) {
        FragmentManager.BackStackEntry backStackEntry = getSupportFragmentManager()
                .getBackStackEntryAt(lastFragmentIndex);

        String fragmentTag = backStackEntry
                .getName();

        currentFragment = getSupportFragmentManager()
                .findFragmentByTag(fragmentTag);
      }
    }
  }
  
  @Override
  protected Fragment getFragment() {
    return currentFragment;
  }
  
  protected void setCurrentFragment(Fragment fragment) {
    currentFragment = fragment;
    changeFragment(currentFragment);
  }
}
