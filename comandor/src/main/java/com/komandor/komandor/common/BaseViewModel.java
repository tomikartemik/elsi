package com.komandor.komandor.common;

import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {
  
  protected CompositeDisposable compositeDisposable = new CompositeDisposable();
  protected Disposable singleDisposable;

  protected void disposeSingleDisposable() {
    if(singleDisposable != null && !singleDisposable.isDisposed()) {
      singleDisposable.dispose();
    }
  }

  @Override
  protected void onCleared() {
    super.onCleared();
    if(compositeDisposable != null) {
      compositeDisposable.dispose();
    }
    
    if(singleDisposable != null) {
      singleDisposable.dispose();
    }
  }
}
