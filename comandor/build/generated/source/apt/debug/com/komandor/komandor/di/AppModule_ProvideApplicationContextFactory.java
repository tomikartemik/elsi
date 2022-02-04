// Generated by Dagger (https://google.github.io/dagger).
package com.komandor.komandor.di;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AppModule_ProvideApplicationContextFactory implements Factory<Context> {
  private final AppModule module;

  public AppModule_ProvideApplicationContextFactory(AppModule module) {
    this.module = module;
  }

  @Override
  public Context get() {
    return proxyProvideApplicationContext(module);
  }

  public static AppModule_ProvideApplicationContextFactory create(AppModule module) {
    return new AppModule_ProvideApplicationContextFactory(module);
  }

  public static Context proxyProvideApplicationContext(AppModule instance) {
    return Preconditions.checkNotNull(
        instance.provideApplicationContext(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
