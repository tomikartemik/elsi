package com.komandor.komandor.utils;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

public class KomandorException extends RuntimeException {
  
  private String tag = "ERROR";
  
  public KomandorException(String message) {
    super(message);
  }
  
  public KomandorException(String TAG, String message) {
    super(message);
    
    tag = TAG;
  }
  
  public KomandorException(Throwable throwable) {
    super(throwable);
  }
  
  @Override
  public void printStackTrace() {
    Log.e(tag, getMessage());
    super.printStackTrace();
  
    Crashlytics.logException(this);
  }
  
  public String getTag() {
    return tag;
  }
  
  public void setTag(String tag) {
    this.tag = tag;
  }
}
