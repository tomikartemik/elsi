package com.komandor.komandor.ui.message_info;

import com.komandor.komandor.common.SingleFragmentActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MessageInfoActivity extends SingleFragmentActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("Сообщение");
    showHomeButton(true);
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
  protected Fragment getFragment() {
    long messageID = getIntent()
            .getLongExtra(MessageInfoFragment.MESSAGE_ID_KEY, 0);
    boolean hasFile = getIntent()
            .getBooleanExtra(MessageInfoFragment.MESSAGE_HAS_FILE_KEY, false);
    return MessageInfoFragment.newInstance(messageID, hasFile);
  }
}
