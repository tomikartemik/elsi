package com.komandor.komandor.common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class CommonActivity extends AppCompatActivity {
  
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayout());
    
  }
  
  protected abstract int getLayout();
  
  public void showHomeButton(boolean show) {
    getSupportActionBar()
            .setHomeButtonEnabled(show);
    getSupportActionBar()
            .setDisplayHomeAsUpEnabled(show);
  }
}
