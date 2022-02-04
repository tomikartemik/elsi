package com.komandor.komandor.ui.main.chats;

import com.komandor.komandor.common.BaseViewModel;
import com.komandor.komandor.data.database.chats.ChatWithLastMessage;
import com.komandor.komandor.utils.KomandorException;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.disposables.Disposable;

public class ChatsViewModel extends BaseViewModel {
  
  ChatsRepository chatsRepository;
  
  Disposable disposable;
  
  private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
  private MutableLiveData<Boolean> hasPermissions = new MutableLiveData<>(false);
  private MutableLiveData<List<ChatWithLastMessage>> chats = new MutableLiveData<>();
  
  private SwipeRefreshLayout.OnRefreshListener onRefreshListener = this::updateChats;
  
  private ChatsAdapter.OnChatItemClickListener onChatItemClickListener;
  
  @Inject
  public ChatsViewModel(ChatsRepository chatsRepository) {
    this.chatsRepository = chatsRepository;
  }
  
  public void updateChats() {
    if (singleDisposable != null && !singleDisposable.isDisposed()) {
      singleDisposable.dispose();
    }

    singleDisposable = chatsRepository.updateChats()
        .subscribe((resource) -> {
          switch (resource.status) {
            case LOADING:
              isLoading.setValue(true);
              break;
            case SUCCESS:
              isLoading.setValue(false);
              chats.setValue(resource.data);
              break;
            case ERROR:
              isLoading.setValue(false);
              new KomandorException(resource.message).printStackTrace();
              break;
          }
        });
  }
  
  public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
    return onRefreshListener;
  }
  
  public MutableLiveData<Boolean> getIsLoading() {
    return isLoading;
  }
  
  public MutableLiveData<Boolean> getHasPermissions() {
    return hasPermissions;
  }
  
  public void setHasPermissions(boolean hasPermissions) {
    this.hasPermissions.setValue(hasPermissions);
  }
  
  public MutableLiveData<List<ChatWithLastMessage>> getChats() {
    return chats;
  }
  
  public ChatsAdapter.OnChatItemClickListener getOnChatItemClickListener() {
    return onChatItemClickListener;
  }
  
  public void setOnChatItemClickListener(ChatsAdapter.OnChatItemClickListener onChatItemClickListener) {
    this.onChatItemClickListener = onChatItemClickListener;
  }
}
