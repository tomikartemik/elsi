package com.komandor.komandor.ui.chat;

import android.view.View;

import com.komandor.komandor.common.BaseViewModel;
import com.komandor.komandor.data.database.chats.Chat;
import com.komandor.komandor.data.database.chats.ChatsStorage;
import com.komandor.komandor.data.database.messages.Message;
import com.komandor.komandor.data.temporary.CryptoStorage;
import com.komandor.komandor.utils.Event;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.lifecycle.MutableLiveData;

public class ChatViewModel extends BaseViewModel {
  public static final int PAGE_SIZE = 10;
  
  
  ChatRepository chatRepository;
  ChatsStorage chatsStorage;
  CryptoStorage cryptoStorage;
  
  private Chat currentChat;
  private MutableLiveData<PagedList<Message>> messages = new MutableLiveData<>();
  private MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
  private MutableLiveData<Event<String>> loadingFinishedEvent = new MutableLiveData<>();
  private MutableLiveData<String> messageText = new MutableLiveData<>("");
  
  private MessagesAdapter.OnMessageItemClickListener onMessageItemClickListener;
  private MessagesAdapter.OnDownloadFileClickListener onDownloadFileClickListener;

  private View.OnClickListener onSendFileClickListener;

  @Inject
  public ChatViewModel(ChatRepository chatRepository, ChatsStorage chatsStorage, CryptoStorage cryptoStorage) {
    this.chatRepository = chatRepository;
    this.chatsStorage = chatsStorage;
    this.cryptoStorage = cryptoStorage;
    
  }
  
  public void initChat(String chatID) {
    singleDisposable = chatsStorage.getChatByID(chatID)
        .flatMap(chat -> {
          currentChat = chat;
          return chatRepository.getMessages(chat, PAGE_SIZE);
        })
        .subscribe((loadedMessages -> {
          messages.setValue(loadedMessages);
          loadingFinishedEvent.setValue(new Event<>(currentChat.getName()));
        }));
  }
  
  public void sendMessage() {
    chatRepository.sendMessage(currentChat, messageText.getValue());
    messageText.setValue("");
  }

  public void sendFile(String filePath) {
    chatRepository.sendFile(currentChat, filePath);
  }
  
  public LiveData<PagedList<Message>> getMessages() {
    return messages;
  }
  
  public MutableLiveData<Boolean> getIsLoading() {
    return isLoading;
  }

  public void setOnMessageItemClickListener(MessagesAdapter.OnMessageItemClickListener onMessageItemClickListener) {
    this.onMessageItemClickListener = onMessageItemClickListener;
  }

  public MessagesAdapter.OnMessageItemClickListener getOnMessageItemClickListener() {
    return onMessageItemClickListener;
  }
  
  public MutableLiveData<Event<String>> getLoadingFinishedEvent() {
    return loadingFinishedEvent;
  }
  
  public MutableLiveData<String> getMessageText() {
    return messageText;
  }
  
  public void setMessageText(String messageText) {
    this.messageText.setValue(messageText);
  }

  public View.OnClickListener getOnSendFileClickListener() {
    return onSendFileClickListener;
  }

  public void setOnSendFileClickListener(View.OnClickListener onSendFileClickListener) {
    this.onSendFileClickListener = onSendFileClickListener;
  }
  
  public MessagesAdapter.OnDownloadFileClickListener getOnDownloadFileClickListener() {
    return onDownloadFileClickListener;
  }
  
  public void setOnDownloadFileClickListener(MessagesAdapter.OnDownloadFileClickListener onDownloadFileClickListener) {
    this.onDownloadFileClickListener = onDownloadFileClickListener;
  }
}
