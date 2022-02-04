package com.komandor.komandor.di;

import android.content.Context;

import com.komandor.komandor.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
  private final App app;
  
  public AppModule(App app) {
    this.app = app;
  }
  
  @Provides
  @Singleton
  App provideApp() {
    return app;
  }
  
  @Provides
  @Singleton
  Context provideApplicationContext() {
    return app.getApplicationContext();
  }

}
