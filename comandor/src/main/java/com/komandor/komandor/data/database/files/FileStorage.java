package com.komandor.komandor.data.database.files;

import com.komandor.komandor.data.api.model.response.MessageResponseModel;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class FileStorage {
  
  FileDao fileDao;
  
  @Inject
  public FileStorage(FileDao fileDao) {
    this.fileDao = fileDao;
  }
  
  public long insertFile(MessageResponseModel message) {
    return fileDao.insertFile(new File(message));
  }
  
  public long insertFile(File file) {
    return fileDao.insertFile(file);
  }
  
  public Flowable<File> getFileByID(long id) {
    return fileDao.getFileByID(id);
  }
  
  public void updateFileID(long localFileID, String fileID) {
    fileDao.updateFileID(localFileID, fileID);
  }
  
  public void updateFilePathByFileID(String fileID, String filePath) {
    fileDao.updateFilePathByFileID(fileID, filePath);
  }
  
  public void updateFilePathByID(long id, String filePath) {
    fileDao.updateFilePathByID(id, filePath);
  }
}
