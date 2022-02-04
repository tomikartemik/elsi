package com.komandor.komandor.ui.message_info;

import com.komandor.komandor.common.BaseViewModel;
import com.komandor.komandor.data.database.contacts.Contact;
import com.komandor.komandor.data.database.files.File;
import com.komandor.komandor.data.database.messages.Message;
import com.komandor.komandor.utils.Event;
import com.komandor.komandor.utils.SystemUtils;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.Disposable;

public class MessageInfoViewModel extends BaseViewModel {
  
  private MessageInfoRepository messageInfoRepository;
  
  private long messageID;
  private MutableLiveData<Message> message = new MutableLiveData<>();
  private MutableLiveData<String> userName = new MutableLiveData<>("");
  private MutableLiveData<String> userDetails = new MutableLiveData<>("");
  private MutableLiveData<String> messageDate = new MutableLiveData<>("");
  
  private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
  private MutableLiveData<Boolean> isFileLoading = new MutableLiveData<>(false);
  private MutableLiveData<Boolean> isVerified = new MutableLiveData<>();
  private MutableLiveData<Boolean> hasFilePath = new MutableLiveData<>(false);
  
  private MutableLiveData<Event<Boolean>> onVerifyEvent = new MutableLiveData<>();
  
  private File messageFile;
  private OnOpenFolderClickListener onOpenFolderClickListener;
  
  @Inject
  public MessageInfoViewModel(MessageInfoRepository messageInfoRepository) {
    this.messageInfoRepository = messageInfoRepository;
  }
  
  public void loadMessageInfo() {
    singleDisposable = messageInfoRepository.loadMessageInfo(messageID)
        .subscribe(resource -> {
          switch (resource.status) {
            case LOADING:
              isLoading.setValue(true);
              break;
            case SUCCESS:
              Message msg = resource.data.getMessage();
              Contact contact = resource.data.getContact();
              if (contact != null && contact.getCompany() != null) {
                userName.setValue(contact.getCompany());
                userDetails.setValue(contact.getFullName());
              } else {
                userName.setValue(msg.getUserName());
              }
              messageDate.setValue(SystemUtils.formatDateToString(msg.getDate()));
              message.setValue(msg);
              messageFile = resource.data.getFile();
  
              singleDisposable = messageInfoRepository.isFileLoaded(messageFile.getId())
                  .doFinally(this::disposeSingleDisposable)
                  .subscribe(loaded -> {
                    hasFilePath.setValue(loaded);
                  });
                  
              
              isLoading.setValue(false);
              break;
            case ERROR:
              isLoading.setValue(false);
              break;
          }
        });
  }
  
  public void verifySign() {
    Message msg = message.getValue();
    
    if (singleDisposable != null && !singleDisposable.isDisposed()) {
      singleDisposable.dispose();
    }
    
    singleDisposable = messageInfoRepository.verifySign(msg.getDecryptedData(), msg.getSign(), msg.getCid())
        .subscribe(verified -> {
          isVerified.setValue(verified);
        });
  }
  
  public void verifyCMS() {
    Message msg = message.getValue();
    
    if (singleDisposable != null && !singleDisposable.isDisposed()) {
      singleDisposable.dispose();
    }
    
    singleDisposable = messageInfoRepository.verifyCMS(messageFile.getFilePath(), messageFile.getCms(), msg.getCid())
        .subscribe(verified -> {
          isVerified.setValue(verified);
        });
  }
  
  public void openFolder() {
    if (messageFile != null && messageFile.getFilePath() != null) {
      java.io.File file = new java.io.File(messageFile.getFilePath());
      if (file.exists()) {
        onOpenFolderClickListener.onOpenFolderClick(file.getParent());
      } else {
      }
    }
  }
  
  public void setMessageID(long messageID) {
    this.messageID = messageID;
  }
  
  public long getMessageID() {
    return messageID;
  }
  
  public OnOpenFolderClickListener getOnOpenFolderClickListener() {
    return onOpenFolderClickListener;
  }
  
  public void setOnOpenFolderClickListener(OnOpenFolderClickListener onOpenFolderClickListener) {
    this.onOpenFolderClickListener = onOpenFolderClickListener;
  }
  
  public MutableLiveData<Message> getMessage() {
    return message;
  }
  
  public MutableLiveData<String> getUserName() {
    return userName;
  }
  
  public MutableLiveData<String> getUserDetails() {
    return userDetails;
  }
  
  public MutableLiveData<String> getMessageDate() {
    return messageDate;
  }
  
  public MutableLiveData<Boolean> getIsLoading() {
    return isLoading;
  }
  
  public MutableLiveData<Boolean> getIsVerified() {
    return isVerified;
  }
  
  public MutableLiveData<Boolean> getHasFilePath() {
    return hasFilePath;
  }
  
  public MutableLiveData<Event<Boolean>> getOnVerifyEvent() {
    return onVerifyEvent;
  }
  
  public MutableLiveData<Boolean> getIsFileLoading() {
    return isFileLoading;
  }
  
  public interface OnOpenFolderClickListener {
    void onOpenFolderClick(String folderPath);
  }
}
