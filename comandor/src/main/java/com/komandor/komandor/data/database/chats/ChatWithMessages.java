package com.komandor.komandor.data.database.chats;

import com.komandor.komandor.data.database.messages.Message;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ChatWithMessages {
  @Embedded
  Chat chat;
  
  @Relation(parentColumn = "chat_id", entityColumn = "chat_id")
  List<Message> messages;
  
  public Chat getChat() {
    return chat;
  }
  
  public List<Message> getMessages() {
    return messages;
  }
}
