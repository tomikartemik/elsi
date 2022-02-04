package com.komandor.komandor.ui.main.profile;

import android.net.Uri;
import android.view.View;

import com.komandor.komandor.common.BaseViewModel;
import com.komandor.komandor.data.database.users.User;
import com.komandor.komandor.utils.Event;
import com.komandor.komandor.utils.KomandorException;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ProfileViewModel extends BaseViewModel {
  
  private String text;
  
  ProfileRepository profileRepository;
  
  private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
  private MutableLiveData<Event<Boolean>> isLogOut = new MutableLiveData<>();
  private MutableLiveData<User> user = new MutableLiveData<>();
  private MutableLiveData<Uri> photo = new MutableLiveData<>();
  
  private SwipeRefreshLayout.OnRefreshListener onRefreshListener = this::updateUser;
  private View.OnClickListener onLogOutClickListener = (v) ->
      compositeDisposable.add(profileRepository.logOut()
          .doFinally(() -> isLogOut.setValue(new Event<>(true)))
          .subscribe()
      );
  
  @Inject
  public ProfileViewModel(ProfileRepository profileRepository) {
    this.profileRepository = profileRepository;
    
    updateUser();
  }
  
  public void updateUser() {
    singleDisposable = profileRepository
            .getProfile()
            .subscribe(resource -> {
      
      switch (resource.status) {
        case LOADING:
          isLoading.setValue(true);
          break;
        case SUCCESS:
          isLoading.setValue(false);
          user.setValue(resource.data);
          break;
        case ERROR:
          isLoading.setValue(false);
          new KomandorException(resource.message).printStackTrace();
          break;
      }
    });
  }
  
  public String getText() {
    return text;
  }
  
  public void setText(String text) {
    this.text = text;
  }
  
  public MutableLiveData<Uri> getPhoto() {
    return photo;
  }
  
  public void setPhoto(Uri photo) {
    this.photo.setValue(photo);
  }
  
  public MutableLiveData<User> getUser() {
    return user;
  }
  
  public MutableLiveData<Boolean> getIsLoading() {
    return isLoading;
  }
  
  public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
    return onRefreshListener;
  }
  
  public MutableLiveData<Event<Boolean>> getIsLogOut() {
    return isLogOut;
  }
  
  public View.OnClickListener getOnLogOutClickListener() {
    return onLogOutClickListener;
  }
  
  @Override
  protected void onCleared() {
    super.onCleared();
  }
}
