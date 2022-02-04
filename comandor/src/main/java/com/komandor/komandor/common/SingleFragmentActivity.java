package com.komandor.komandor.common;

import android.os.Bundle;

import com.komandor.komandor.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public abstract class SingleFragmentActivity extends CommonActivity {
  
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  
    if (savedInstanceState == null) {
      changeFragment(getFragment());
    }
  }
  
  @Override
  protected int getLayout() {
    return R.layout.ac_single_fagment;
  }
  
  public void changeFragment(Fragment fragment) {
    if(fragment == null) {
      return;
    }
    
    boolean addToBackStack = getSupportFragmentManager().findFragmentById(R.id.singleFragmentFragmentContainer) != null;
  
    FragmentTransaction transaction = getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.singleFragmentFragmentContainer, fragment, fragment.getClass().getName());
  
    if (addToBackStack) {
      transaction.addToBackStack(fragment.getClass().getSimpleName());
    }
  
    transaction.commit();
  }
  
  protected abstract Fragment getFragment();
}
