package com.komandor.komandor.ui.chat;

import com.komandor.komandor.common.BaseViewModel;
import com.komandor.komandor.data.database.messages.Message;
import com.komandor.komandor.utils.SystemUtils;

public class MessageItemViewModel extends BaseViewModel {
  private long id;
  private String message;
  private String date;
  private long localFileID;

  private boolean isSent;
  private boolean isGrouped;
  private boolean hasFile = false;

  public MessageItemViewModel(Message message) {
    this.id = message.getId();
    this.message = message.getDecryptedData();
    this.isGrouped = message.isGrouped();

    if (message.getFileID() != 0) {
      this.localFileID = message.getFileID();
      this.hasFile = true;
    }

    this.date = SystemUtils.formatDateToString(message.getDate());

    this.isSent = message
            .getMessageID() != 0;
  }

  public long getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }

  public String getDate() {
    return date;
  }

  public boolean isSent() {
    return isSent;
  }

  public boolean hasFile() {
    return hasFile;
  }
  
  public boolean isGrouped() {
    return isGrouped;
  }
  
  public long getLocalFileID() {
    return localFileID;
  }
}
