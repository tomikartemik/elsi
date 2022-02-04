// Generated by Dagger (https://google.github.io/dagger).
package com.komandor.komandor.ui.message_info;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class MessageInfoViewModel_Factory implements Factory<MessageInfoViewModel> {
  private final Provider<MessageInfoRepository> messageInfoRepositoryProvider;

  public MessageInfoViewModel_Factory(
      Provider<MessageInfoRepository> messageInfoRepositoryProvider) {
    this.messageInfoRepositoryProvider = messageInfoRepositoryProvider;
  }

  @Override
  public MessageInfoViewModel get() {
    return new MessageInfoViewModel(messageInfoRepositoryProvider.get());
  }

  public static MessageInfoViewModel_Factory create(
      Provider<MessageInfoRepository> messageInfoRepositoryProvider) {
    return new MessageInfoViewModel_Factory(messageInfoRepositoryProvider);
  }

  public static MessageInfoViewModel newMessageInfoViewModel(
      MessageInfoRepository messageInfoRepository) {
    return new MessageInfoViewModel(messageInfoRepository);
  }
}