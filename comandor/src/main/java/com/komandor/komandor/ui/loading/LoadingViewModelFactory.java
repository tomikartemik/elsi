package com.komandor.komandor.ui.loading;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoadingViewModelFactory extends ViewModelProvider.NewInstanceFactory {
  private LoadingViewModel.OnNavigateListener onNavigateListener;
  
  
  public LoadingViewModelFactory(LoadingViewModel.OnNavigateListener onNavigateListener){
    this.onNavigateListener = onNavigateListener;
  }
  
  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    return (T) new LoadingViewModel(onNavigateListener);
  }
}
