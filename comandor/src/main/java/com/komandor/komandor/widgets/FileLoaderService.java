package com.komandor.komandor.widgets;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import io.reactivex.disposables.Disposable;

public class FileLoaderService extends Service {
  
  public static final String NAME = "FILE_LOADER_SERVICE";
  public static final String FILE_ID_KEY = "FILE_ID_KEY";
  public static final String CHAT_ID_KEY = "CHAT_ID_KEY";

  
  private Disposable disposable;
  
  
  public FileLoaderService() {
    super();
  }
  
  @Override
  public void onCreate() {
    //App.getAppComponent().inject(this);
    super.onCreate();
  }
  
  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    if (intent != null) {
      long fileID = intent.getLongExtra(FILE_ID_KEY, 0);
      String chatID = intent.getStringExtra(CHAT_ID_KEY);
      
    }
    
    return START_REDELIVER_INTENT;
  }
  
  private void disposeDisposable() {
    if (disposable != null) {
      disposable.dispose();
    }
  }
  
  @Override
  public void onDestroy() {
    super.onDestroy();
    disposeDisposable();
  }
  
  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}
