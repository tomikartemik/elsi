// Generated by Dagger (https://google.github.io/dagger).
package com.komandor.komandor.data.database.messages;

import com.komandor.komandor.data.database.files.FileStorage;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class MessagesStorage_Factory implements Factory<MessagesStorage> {
  private final Provider<MessageDao> messageDaoProvider;

  private final Provider<FileStorage> fileStorageProvider;

  public MessagesStorage_Factory(
      Provider<MessageDao> messageDaoProvider, Provider<FileStorage> fileStorageProvider) {
    this.messageDaoProvider = messageDaoProvider;
    this.fileStorageProvider = fileStorageProvider;
  }

  @Override
  public MessagesStorage get() {
    return new MessagesStorage(messageDaoProvider.get(), fileStorageProvider.get());
  }

  public static MessagesStorage_Factory create(
      Provider<MessageDao> messageDaoProvider, Provider<FileStorage> fileStorageProvider) {
    return new MessagesStorage_Factory(messageDaoProvider, fileStorageProvider);
  }

  public static MessagesStorage newMessagesStorage(MessageDao messageDao, FileStorage fileStorage) {
    return new MessagesStorage(messageDao, fileStorage);
  }
}