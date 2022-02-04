package com.komandor.komandor.ui.main.chats;

import com.komandor.komandor.common.BaseViewModel;
import com.komandor.komandor.data.database.chats.ChatWithLastMessage;

public class ChatItemViewModel extends BaseViewModel {
  private String chatID;
  private String name;
  private String lastMessage = "Нет сообщений";
  private int unread;
  
  public ChatItemViewModel(ChatWithLastMessage chat) {
    chatID = chat.getChat().getChatID();
    name = chat.getChat().getName();
    
    unread = chat.getChat().getUnreadMessages();
    
    if (chat.getMessage() != null) {
      lastMessage = chat.getMessage().getDecryptedData();
    }
  }
  
  public String getChatID() {
    return chatID;
  }
  
  public String getName() {
    return name;
  }
  
  public String getLastMessage() {
    return lastMessage;
  }
  
  public int getUnread() {
    return unread;
  }
  
  public String getStringUnread() {
    return Integer.toString(unread);
  }
}
