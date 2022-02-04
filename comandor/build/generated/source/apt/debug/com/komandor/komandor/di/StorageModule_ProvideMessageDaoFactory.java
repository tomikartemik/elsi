// Generated by Dagger (https://google.github.io/dagger).
package com.komandor.komandor.di;

import com.komandor.komandor.data.database.KomandorDatabase;
import com.komandor.komandor.data.database.messages.MessageDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class StorageModule_ProvideMessageDaoFactory implements Factory<MessageDao> {
  private final StorageModule module;

  private final Provider<KomandorDatabase> dbProvider;

  public StorageModule_ProvideMessageDaoFactory(
      StorageModule module, Provider<KomandorDatabase> dbProvider) {
    this.module = module;
    this.dbProvider = dbProvider;
  }

  @Override
  public MessageDao get() {
    return proxyProvideMessageDao(module, dbProvider.get());
  }

  public static StorageModule_ProvideMessageDaoFactory create(
      StorageModule module, Provider<KomandorDatabase> dbProvider) {
    return new StorageModule_ProvideMessageDaoFactory(module, dbProvider);
  }

  public static MessageDao proxyProvideMessageDao(StorageModule instance, KomandorDatabase db) {
    return Preconditions.checkNotNull(
        instance.provideMessageDao(db), "Cannot return null from a non-@Nullable @Provides method");
  }
}
