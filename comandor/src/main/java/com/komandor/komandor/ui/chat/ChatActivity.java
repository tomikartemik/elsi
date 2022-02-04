package com.komandor.komandor.ui.chat;

import android.os.Bundle;
import android.view.MenuItem;

import com.komandor.komandor.common.SingleFragmentActivity;
import com.komandor.komandor.utils.Constants;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChatActivity extends SingleFragmentActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("");
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
    String chatID = getIntent().getExtras().getString(Constants.INTENT_EXTRA_CHAT_ID);
    return ChatFragment.newInstance(chatID);
  }
}
