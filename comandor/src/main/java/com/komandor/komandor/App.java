package com.komandor.komandor;

import android.app.Application;

import com.komandor.komandor.di.AppComponent;
import com.komandor.komandor.di.AppModule;
import com.komandor.komandor.di.DaggerAppComponent;
import com.komandor.komandor.di.NetworkModule;
import com.komandor.komandor.di.StorageModule;

public class App extends Application {
  
  private static AppComponent sAppComponent;
  
  @Override
  public void onCreate() {
    super.onCreate();
    sAppComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .networkModule(new NetworkModule())
        .storageModule(new StorageModule())
        .build();
  }
  
  public static AppComponent getAppComponent() {
    return sAppComponent;
  }
}
