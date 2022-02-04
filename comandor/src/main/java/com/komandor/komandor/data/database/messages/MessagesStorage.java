package com.komandor.komandor.data.database.messages;

import com.komandor.komandor.data.api.model.response.MessageResponseModel;
import com.komandor.komandor.data.database.files.FileStorage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class MessagesStorage {

  MessageDao messageDao;
  FileStorage fileStorage;

  @Inject
  public MessagesStorage(MessageDao messageDao, FileStorage fileStorage) {
    this.messageDao = messageDao;
    this.fileStorage = fileStorage;
  }

  public long insert(MessageResponseModel messageResponseModel) {
    long fileID = 0;
    if(messageResponseModel.getFileID() != null && !messageResponseModel.getFileID().equals("")) {
      fileID = fileStorage.insertFile(messageResponseModel);
    }

    return messageDao.insertMessage(new Message(messageResponseModel, fileID));
  }

  public long insertLocalMessage(Message message) {
    return messageDao.insert(message);
  }

  public void insertMany(List<MessageResponseModel> messages) {
    List<Message> insertMessages = new ArrayList<>();

    for(MessageResponseModel message : messages) {
      long fileID = 0;
      if(message.getFileID() != null && !message.getFileID().equals("")) {
        fileID = fileStorage.insertFile(message);
      }

      insertMessages.add(new Message(message, fileID));
    }

    messageDao.insertManyMessages(insertMessages);
  }

  public Flowable<Message> getMessageByID(long id) {
    return messageDao.getMessageByID(id);
  }

  public int getLastMessageID(String chatID) {
    return messageDao.getLastMessageID(chatID);
  }

  public List<Integer> getAllKeys(String chatID) {
    return messageDao.getAllKeys(chatID);
  }

  public List<Message> getMessagesByIDs(List<Integer> ids) {
    return messageDao.getMessagesByIDs(ids);
  }

  public void updateMessage(MessageResponseModel data) {
    messageDao.updateMessage(new Message(data));
  }

  public Flowable<MessageWithFileWithContact> getMessageWithFileWithContactByID(long id) {
    return messageDao.getMessageWithFileWithContactByID(id);
  }
}
