// Generated by Dagger (https://google.github.io/dagger).
package com.komandor.komandor.di;

import com.komandor.komandor.data.api.KomandorAPI;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class NetworkModule_ProvideApiServiceFactory implements Factory<KomandorAPI> {
  private final NetworkModule module;

  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideApiServiceFactory(
      NetworkModule module, Provider<Retrofit> retrofitProvider) {
    this.module = module;
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public KomandorAPI get() {
    return proxyProvideApiService(module, retrofitProvider.get());
  }

  public static NetworkModule_ProvideApiServiceFactory create(
      NetworkModule module, Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideApiServiceFactory(module, retrofitProvider);
  }

  public static KomandorAPI proxyProvideApiService(NetworkModule instance, Retrofit retrofit) {
    return Preconditions.checkNotNull(
        instance.provideApiService(retrofit),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}