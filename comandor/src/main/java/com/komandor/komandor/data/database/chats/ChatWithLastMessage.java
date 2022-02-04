package com.komandor.komandor.data.database.chats;

import com.komandor.komandor.data.database.messages.Message;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ChatWithLastMessage {
  
  @Embedded
  public Chat chat;
  
  @Relation(parentColumn = "last_message_id", entityColumn = "id")
  public List<Message> messages;
  
  
  public Chat getChat() {
    return chat;
  }
  
  public Message getMessage() {
    if(messages.size() <= 0) {
      return null;
    }
  
    return messages.get(0);
  
  }
}
