package com.komandor.komandor.utils;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

public class KomandorLog {

  public static void log(String TAG, String message) {
    Crashlytics.log(1, TAG, message);
    Log.i(TAG, message);
  }

}
