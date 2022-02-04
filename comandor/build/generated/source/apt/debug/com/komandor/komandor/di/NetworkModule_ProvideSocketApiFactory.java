// Generated by Dagger (https://google.github.io/dagger).
package com.komandor.komandor.di;

import android.content.Context;
import com.komandor.komandor.data.api.SocketAPI;
import com.komandor.komandor.data.database.chats.ChatsStorage;
import com.komandor.komandor.data.database.files.FileStorage;
import com.komandor.komandor.data.database.messages.MessagesStorage;
import com.komandor.komandor.data.temporary.TemporaryStorage;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NetworkModule_ProvideSocketApiFactory implements Factory<SocketAPI> {
  private final NetworkModule module;

  private final Provider<Context> contextProvider;

  private final Provider<ChatsStorage> chatsStorageProvider;

  private final Provider<MessagesStorage> messagesStorageProvider;

  private final Provider<FileStorage> fileStorageProvider;

  private final Provider<TemporaryStorage> temporaryStorageProvider;

  public NetworkModule_ProvideSocketApiFactory(
      NetworkModule module,
      Provider<Context> contextProvider,
      Provider<ChatsStorage> chatsStorageProvider,
      Provider<MessagesStorage> messagesStorageProvider,
      Provider<FileStorage> fileStorageProvider,
      Provider<TemporaryStorage> temporaryStorageProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
    this.chatsStorageProvider = chatsStorageProvider;
    this.messagesStorageProvider = messagesStorageProvider;
    this.fileStorageProvider = fileStorageProvider;
    this.temporaryStorageProvider = temporaryStorageProvider;
  }

  @Override
  public SocketAPI get() {
    return proxyProvideSocketApi(
        module,
        contextProvider.get(),
        chatsStorageProvider.get(),
        messagesStorageProvider.get(),
        fileStorageProvider.get(),
        temporaryStorageProvider.get());
  }

  public static NetworkModule_ProvideSocketApiFactory create(
      NetworkModule module,
      Provider<Context> contextProvider,
      Provider<ChatsStorage> chatsStorageProvider,
      Provider<MessagesStorage> messagesStorageProvider,
      Provider<FileStorage> fileStorageProvider,
      Provider<TemporaryStorage> temporaryStorageProvider) {
    return new NetworkModule_ProvideSocketApiFactory(
        module,
        contextProvider,
        chatsStorageProvider,
        messagesStorageProvider,
        fileStorageProvider,
        temporaryStorageProvider);
  }

  public static SocketAPI proxyProvideSocketApi(
      NetworkModule instance,
      Context context,
      ChatsStorage chatsStorage,
      MessagesStorage messagesStorage,
      FileStorage fileStorage,
      TemporaryStorage temporaryStorage) {
    return Preconditions.checkNotNull(
        instance.provideSocketApi(
            context, chatsStorage, messagesStorage, fileStorage, temporaryStorage),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}