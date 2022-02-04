package com.komandor.komandor.ui.message_info;

import com.komandor.komandor.utils.NetworkBoundResource;
import com.komandor.komandor.data.api.KomandorAPI;
import com.komandor.komandor.data.api.model.ResponseModel;
import com.komandor.komandor.data.database.certificates.CertificateDao;
import com.komandor.komandor.data.database.chats.ChatsStorage;
import com.komandor.komandor.data.database.files.FileStorage;
import com.komandor.komandor.data.database.messages.Message;
import com.komandor.komandor.data.database.messages.MessageWithFileWithContact;
import com.komandor.komandor.data.database.messages.MessagesStorage;
import com.komandor.komandor.data.temporary.CryptoStorage;
import com.komandor.komandor.data.temporary.TemporaryStorage;
import com.komandor.komandor.utils.CryptoUtils;
import com.komandor.komandor.utils.Resource;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.CryptoPro.JCP.tools.Array;

@Singleton
public class MessageInfoRepository {
  
  KomandorAPI komandorAPI;
  TemporaryStorage temporaryStorage;
  CryptoStorage cryptoStorage;
  CertificateDao certificateDao;
  ChatsStorage chatsStorage;
  MessagesStorage messagesStorage;
  FileStorage fileStorage;
  
  private Disposable disposable;
  
  @Inject
  public MessageInfoRepository(
      KomandorAPI komandorAPI,
      TemporaryStorage temporaryStorage,
      CryptoStorage cryptoStorage,
      CertificateDao certificateDao,
      ChatsStorage chatsStorage,
      MessagesStorage messagesStorage,
      FileStorage fileStorage
  ) {
    this.komandorAPI = komandorAPI;
    this.temporaryStorage = temporaryStorage;
    this.cryptoStorage = cryptoStorage;
    this.certificateDao = certificateDao;
    this.chatsStorage = chatsStorage;
    this.messagesStorage = messagesStorage;
    this.fileStorage = fileStorage;
  }
  
  public Flowable<Resource<MessageWithFileWithContact>> loadMessageInfo(long localMessageID) {
    return new NetworkBoundResource<MessageWithFileWithContact, Object>() {
      
      @Override
      protected void saveCallResult(Object message) {
      }
      
      @Override
      protected boolean shouldFetch() {
        return false;
      }
      
      @Override
      protected Flowable<MessageWithFileWithContact> loadData() {
        return messagesStorage.getMessageWithFileWithContactByID(localMessageID)
            .flatMap(messageWithFileWithContact -> {
                  Message message = messageWithFileWithContact.getMessage();
                  return chatsStorage.getChatByID(message.getChatID())
                      .map(chat -> chatsStorage.getChatSessionKey(chat))
                      .flatMap(sessionKey -> {
                        String decryptedData = cryptoStorage.decryptMessage(message, sessionKey);
                        message.setDecryptedData(decryptedData);
                        return Flowable.just(messageWithFileWithContact);
                      });
                }
            );
      }
      
      @Override
      protected Flowable<ResponseModel<Object>> createCall() {
        return null;
      }
    }.asFlowable();
  }
  
  public Flowable<Boolean> verifySign(String decryptedMessage, String sign, int cid) {
    return Flowable.fromCallable(() -> {
      if (cid == cryptoStorage.getUserCID()) {
        return cryptoStorage.verifySign(decryptedMessage, sign);
      }
      
      String certBase64 = certificateDao.getCertificateStringByIDSync(cid);
      return cryptoStorage.verifySign(decryptedMessage, sign, CryptoUtils.decodeBase64Certificate(certBase64));
    })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
  
  public Flowable<Boolean> verifyCMS(String filePath, String cms, int cid) {
    return Flowable.fromCallable(() -> {
      byte[] bFile = Array.readFile(new File(filePath));
      
      if (cid == cryptoStorage.getUserCID()) {
        return cryptoStorage.verifyCMS(bFile, cms);
      } else {
        String certBase64 = certificateDao.getCertificateStringByIDSync(cid);
        return cryptoStorage.verifyCMS(bFile, cms, CryptoUtils.decodeBase64Certificate(certBase64));
      }
    });
  }
  
  public Flowable<Boolean> isFileLoaded(long fileID) {
    return fileStorage.getFileByID(fileID)
        .map(file -> {
          String filePath = file.getFilePath();
          if (filePath != null && !filePath.equals("")) {
            File localFile = new File(filePath);
            if (localFile.exists()) {
              return true;
            }
            
            fileStorage.updateFilePathByID(fileID, null);
          }
          
          return false;
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
