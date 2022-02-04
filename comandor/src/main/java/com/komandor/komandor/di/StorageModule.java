package com.komandor.komandor.di;

import com.komandor.komandor.data.database.KomandorDatabase;
import com.komandor.komandor.data.database.chats.ChatDao;
import com.komandor.komandor.data.database.contacts.ContactDao;
import com.komandor.komandor.data.database.contacts.ContactsCertificatesDao;
import com.komandor.komandor.data.database.files.FileDao;
import com.komandor.komandor.data.database.messages.MessageDao;
import com.komandor.komandor.App;
import com.komandor.komandor.data.database.certificates.CertificateDao;
import com.komandor.komandor.data.database.users.UserDao;
import com.komandor.komandor.data.temporary.CryptoStorage;
import com.komandor.komandor.data.temporary.TemporaryStorage;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {
  
  @Provides
  @Singleton
  KomandorDatabase provideDatabase(App app) {
    return Room.databaseBuilder(app, KomandorDatabase.class, "komandor_database")
        .fallbackToDestructiveMigration()
        .build();
  }
  
  @Provides
  @Singleton
  UserDao provideUserDao(KomandorDatabase db) {
    return db.userDao();
  }
  
  @Provides
  @Singleton
  CertificateDao provideCertificateDao(KomandorDatabase db) {
    return db.certificateDao();
  }
  
  @Provides
  @Singleton
  ContactDao provideContactDao(KomandorDatabase db) {
    return db.contactDao();
  }
  
  @Provides
  @Singleton
  ChatDao provideChatDao(KomandorDatabase db) {
    return db.chatDao();
  }

  @Provides
  @Singleton
  MessageDao provideMessageDao(KomandorDatabase db) {
    return db.messageDao();
  }

  @Provides
  @Singleton
  FileDao provideFileDao(KomandorDatabase db) {
    return db.fileDao();
  }
  
  @Provides
  @Singleton
  ContactsCertificatesDao provideContactCertificatesDao(KomandorDatabase db) {
    return db.contactsCertificatesDao();
  }
  
  @Provides
  @Singleton
  TemporaryStorage provideTemporaryStorage() {
    return new TemporaryStorage();
  }
  
  @Provides
  @Singleton
  CryptoStorage provideCryptoStorage() {
    return new CryptoStorage();
  }
}
