// Generated by Dagger (https://google.github.io/dagger).
package com.komandor.komandor.di;

import com.komandor.komandor.data.temporary.CryptoStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class StorageModule_ProvideCryptoStorageFactory implements Factory<CryptoStorage> {
  private final StorageModule module;

  public StorageModule_ProvideCryptoStorageFactory(StorageModule module) {
    this.module = module;
  }

  @Override
  public CryptoStorage get() {
    return proxyProvideCryptoStorage(module);
  }

  public static StorageModule_ProvideCryptoStorageFactory create(StorageModule module) {
    return new StorageModule_ProvideCryptoStorageFactory(module);
  }

  public static CryptoStorage proxyProvideCryptoStorage(StorageModule instance) {
    return Preconditions.checkNotNull(
        instance.provideCryptoStorage(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
