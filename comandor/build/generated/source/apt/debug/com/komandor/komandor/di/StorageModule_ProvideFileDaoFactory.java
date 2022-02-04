// Generated by Dagger (https://google.github.io/dagger).
package com.komandor.komandor.di;

import com.komandor.komandor.data.database.KomandorDatabase;
import com.komandor.komandor.data.database.files.FileDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class StorageModule_ProvideFileDaoFactory implements Factory<FileDao> {
  private final StorageModule module;

  private final Provider<KomandorDatabase> dbProvider;

  public StorageModule_ProvideFileDaoFactory(
      StorageModule module, Provider<KomandorDatabase> dbProvider) {
    this.module = module;
    this.dbProvider = dbProvider;
  }

  @Override
  public FileDao get() {
    return proxyProvideFileDao(module, dbProvider.get());
  }

  public static StorageModule_ProvideFileDaoFactory create(
      StorageModule module, Provider<KomandorDatabase> dbProvider) {
    return new StorageModule_ProvideFileDaoFactory(module, dbProvider);
  }

  public static FileDao proxyProvideFileDao(StorageModule instance, KomandorDatabase db) {
    return Preconditions.checkNotNull(
        instance.provideFileDao(db), "Cannot return null from a non-@Nullable @Provides method");
  }
}
