package com.komandor.komandor.ui.startinfo;

import com.komandor.komandor.common.SingleFragmentActivity;

import androidx.fragment.app.Fragment;

public class StartInfoActivity extends SingleFragmentActivity {
  
  @Override
  protected Fragment getFragment() {
    return StartInfoFragment.newInstance();
  }
  
}
