// Generated by Dagger (https://google.github.io/dagger).
package com.komandor.komandor.di;

import com.komandor.komandor.data.database.KomandorDatabase;
import com.komandor.komandor.data.database.contacts.ContactsCertificatesDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class StorageModule_ProvideContactCertificatesDaoFactory
    implements Factory<ContactsCertificatesDao> {
  private final StorageModule module;

  private final Provider<KomandorDatabase> dbProvider;

  public StorageModule_ProvideContactCertificatesDaoFactory(
      StorageModule module, Provider<KomandorDatabase> dbProvider) {
    this.module = module;
    this.dbProvider = dbProvider;
  }

  @Override
  public ContactsCertificatesDao get() {
    return proxyProvideContactCertificatesDao(module, dbProvider.get());
  }

  public static StorageModule_ProvideContactCertificatesDaoFactory create(
      StorageModule module, Provider<KomandorDatabase> dbProvider) {
    return new StorageModule_ProvideContactCertificatesDaoFactory(module, dbProvider);
  }

  public static ContactsCertificatesDao proxyProvideContactCertificatesDao(
      StorageModule instance, KomandorDatabase db) {
    return Preconditions.checkNotNull(
        instance.provideContactCertificatesDao(db),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}