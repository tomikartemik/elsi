// Generated by Dagger (https://google.github.io/dagger).
package com.komandor.komandor.di;

import com.google.gson.Gson;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class NetworkModule_ProvideGsonFactory implements Factory<Gson> {
  private final NetworkModule module;

  public NetworkModule_ProvideGsonFactory(NetworkModule module) {
    this.module = module;
  }

  @Override
  public Gson get() {
    return proxyProvideGson(module);
  }

  public static NetworkModule_ProvideGsonFactory create(NetworkModule module) {
    return new NetworkModule_ProvideGsonFactory(module);
  }

  public static Gson proxyProvideGson(NetworkModule instance) {
    return Preconditions.checkNotNull(
        instance.provideGson(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
