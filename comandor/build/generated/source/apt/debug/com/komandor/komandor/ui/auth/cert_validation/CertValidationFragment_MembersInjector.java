// Generated by Dagger (https://google.github.io/dagger).
package com.komandor.komandor.ui.auth.cert_validation;

import com.komandor.komandor.data.temporary.TemporaryStorage;
import com.komandor.komandor.viewmodel.ViewModelFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CertValidationFragment_MembersInjector
    implements MembersInjector<CertValidationFragment> {
  private final Provider<ViewModelFactory> viewModelFactoryProvider;

  private final Provider<TemporaryStorage> temporaryStorageProvider;

  public CertValidationFragment_MembersInjector(
      Provider<ViewModelFactory> viewModelFactoryProvider,
      Provider<TemporaryStorage> temporaryStorageProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
    this.temporaryStorageProvider = temporaryStorageProvider;
  }

  public static MembersInjector<CertValidationFragment> create(
      Provider<ViewModelFactory> viewModelFactoryProvider,
      Provider<TemporaryStorage> temporaryStorageProvider) {
    return new CertValidationFragment_MembersInjector(
        viewModelFactoryProvider, temporaryStorageProvider);
  }

  @Override
  public void injectMembers(CertValidationFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
    injectTemporaryStorage(instance, temporaryStorageProvider.get());
  }

  public static void injectViewModelFactory(
      CertValidationFragment instance, ViewModelFactory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }

  public static void injectTemporaryStorage(
      CertValidationFragment instance, TemporaryStorage temporaryStorage) {
    instance.temporaryStorage = temporaryStorage;
  }
}
