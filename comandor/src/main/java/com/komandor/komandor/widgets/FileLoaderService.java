package com.komandor.komandor.widgets;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.komandor.komandor.App;
import com.komandor.komandor.data.api.KomandorAPI;
import com.komandor.komandor.data.api.model.request.GetFileRequestModel;
import com.komandor.komandor.data.database.chats.ChatsStorage;
import com.komandor.komandor.data.database.files.FileStorage;
import com.komandor.komandor.data.temporary.CryptoStorage;
import com.komandor.komandor.data.temporary.TemporaryStorage;
import com.komandor.komandor.utils.KomandorException;
import com.komandor.komandor.utils.SystemUtils;

import java.io.File;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FileLoaderService extends Service {
  
  public static final String NAME = "FILE_LOADER_SERVICE";
  public static final String FILE_ID_KEY = "FILE_ID_KEY";
  public static final String CHAT_ID_KEY = "CHAT_ID_KEY";
  
  @Inject
  TemporaryStorage temporaryStorage;
  @Inject
  KomandorAPI komandorAPI;
  @Inject
  FileStorage fileStorage;
  @Inject
  ChatsStorage chatsStorage;
  @Inject
  CryptoStorage cryptoStorage;
  
  private Disposable disposable;
  
  
  public FileLoaderService() {
    super();
  }
  
  @Override
  public void onCreate() {
    App.getAppComponent().inject(this);
    super.onCreate();
  }
  
  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    if (intent != null) {
      long fileID = intent.getLongExtra(FILE_ID_KEY, 0);
      String chatID = intent.getStringExtra(CHAT_ID_KEY);
      
      if (fileID != 0 && chatID != null) {
        disposable = fileStorage.getFileByID(fileID)
            .flatMap(file -> {
              GetFileRequestModel getFileRequestModel = new GetFileRequestModel(temporaryStorage.getToken(), chatID, file.getFileID());
              return komandorAPI.getFile(getFileRequestModel)
                  .map(response -> {
                    if (!response.isSuccessful()) {
                      throw new KomandorException(response.getError());
                    }
                    
                    return response.getData();
                  })
                  .flatMap(data ->
                      chatsStorage.getChatByID(chatID)
                          .map(chat -> chatsStorage.getChatSessionKey(chat))
                          .map(encryptedSessionKey -> cryptoStorage.decryptSessionKey(encryptedSessionKey))
                          .map(decryptedSessionKey -> {
                            String fullFileName = cryptoStorage.decryptString(file.getName(), decryptedSessionKey);
                            
                            byte[] decryptedFile = cryptoStorage.decryptFile(data, decryptedSessionKey);
                            byte[] fileCMS = SystemUtils.decodeBase64(file.getCms());
                            
                            File savedFile = SystemUtils.saveFile(fullFileName, decryptedFile);
                            SystemUtils.saveFile(SystemUtils.getFileName(savedFile.getName()) + ".sig", fileCMS);
                            
                            disposeDisposable();
                            fileStorage.updateFilePathByID(fileID, savedFile.getAbsolutePath());
                            return true;
                          })
                  );
            })
            .doOnError(throwable -> {
              new KomandorException(throwable).printStackTrace();
            })
            .doFinally(() -> stopSelf(startId))
            .subscribeOn(Schedulers.io())
            .subscribe();
      }
    }
    
    return START_REDELIVER_INTENT;
  }
  
  private void disposeDisposable() {
    if (disposable != null) {
      disposable.dispose();
    }
  }
  
  @Override
  public void onDestroy() {
    super.onDestroy();
    disposeDisposable();
  }
  
  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}
