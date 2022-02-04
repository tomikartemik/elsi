package com.komandor.komandor.data.database.chats;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import io.reactivex.Flowable;

@Dao
public abstract class ChatDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insert(Chat chat);
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insertMany(List<Chat> chats);
  
  @Query("select * from " + Chat.TABLE_NAME)
  public abstract Flowable<List<Chat>> getChats();
  
  @Transaction
  @Query("select * from " + Chat.TABLE_NAME + " order by name")
  public abstract Flowable<List<ChatWithLastMessage>> getChatsWithLastMessage();
  
  @Transaction
  @Query("select * from " + Chat.TABLE_NAME + " where chat_id like :chatID order by name")
  public abstract Flowable<ChatWithMessages> getChatWithMessages(String chatID);
  
  @Query("select * from " + Chat.TABLE_NAME + " where chat_id = :chatID")
  public abstract Flowable<Chat> getChatByID(String chatID);
  
  @Query("select * from " + Chat.TABLE_NAME + " where chat_id = :chatID")
  public abstract Chat getChatByIDSync(String chatID);
  
  @Query("update " + Chat.TABLE_NAME + " set unread_messages = :unreadMessages where chat_id = :chatID")
  public abstract void setUnreadMessages(String chatID, int unreadMessages);
  
  @Query("update " + Chat.TABLE_NAME + " set is_updated = 0")
  public abstract void setAllChatsOld();
  
  @Query("delete from " + Chat.TABLE_NAME + " where is_updated = 0")
  public abstract void deleteOldChats();
  
  @Transaction
  public void insertChats(List<Chat> chats) {
    setAllChatsOld();
    insertMany(chats);
    deleteOldChats();
  }
  
  @Transaction
  public void increaseUnreadMessages(String chatID) {
    Chat chat = getChatByIDSync(chatID);
    setUnreadMessages(chatID, chat.getUnreadMessages() + 1);
  }
  
  @Query("update " + Chat.TABLE_NAME + " set last_message_id = :id where chat_id = :chatID")
  public abstract void setLastMessageID(String chatID, long id);
}
