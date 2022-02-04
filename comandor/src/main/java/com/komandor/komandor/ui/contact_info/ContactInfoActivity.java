package com.komandor.komandor.ui.contact_info;

import android.os.Bundle;
import android.view.MenuItem;

import com.komandor.komandor.common.SingleFragmentActivity;
import com.komandor.komandor.utils.Constants;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContactInfoActivity extends SingleFragmentActivity implements ContactInfoFragment.OnContactLoadFinishedListener {
  
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("");
    showHomeButton(true);
  }
  
  @Override
  protected Fragment getFragment() {
    int contactID = getIntent()
            .getIntExtra(Constants.INTENT_EXTRA_CONTACT_ID, -1);
    return ContactInfoFragment.newInstance(contactID);
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
  public void onContactLoadFinished(String title) {
    setTitle(title);
  }
}
