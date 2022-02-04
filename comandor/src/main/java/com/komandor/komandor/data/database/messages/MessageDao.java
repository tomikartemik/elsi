package com.komandor.komandor.data.database.messages;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import io.reactivex.Flowable;

@Dao
public abstract class MessageDao {
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract long insert(Message message);
  
  @Update(onConflict = OnConflictStrategy.REPLACE)
  public abstract void update(Message message);

  @Query("select * from " + Message.TABLE_NAME + " where message_id = :messageID")
  public abstract Message getMessageByMessageIDSync(int messageID);

  @Query("select * from " + Message.TABLE_NAME + " where id = :id")
  public abstract Message getMessageByIDSync(long id);

  @Query("select * from " + Message.TABLE_NAME + " where id = :id")
  public abstract Flowable<Message> getMessageByID(long id);

  @Transaction
  @Query("select * from " + Message.TABLE_NAME + " where id = :id")
  public abstract Flowable<MessageWithFileWithContact> getMessageWithFileWithContactByID(long id);

  @Query("select id from " + Message.TABLE_NAME + " where chat_id like :chatID order by date desc limit 1")
  public abstract int getLastMessageID(String chatID);

  @Query("select id from " + Message.TABLE_NAME + " where chat_id like :chatID order by date desc")
  public abstract List<Integer> getAllKeys(String chatID);

  @Query("select * from " + Message.TABLE_NAME + " where id in (:ids) order by date desc")
  public abstract List<Message> getMessagesByIDs(List<Integer> ids);

  @Transaction
  public long insertMessage(Message message) {
    Message dbMessage = getMessageByMessageIDSync(message.getMessageID());
    if (dbMessage != null) {
      message.setId(dbMessage.getId());
      update(message);
      return dbMessage.getId();
    }

    return insert(message);
  }

  @Transaction
  public void insertManyMessages(List<Message> messages) {
    for (Message message : messages) {
      insertMessage(message);
    }
  }

  @Transaction
  public void updateMessage(Message message) {
    Message dbMessage = getMessageByIDSync(message.getId());

    if(dbMessage != null) {
     message.setFileID(dbMessage.getFileID());
     update(message);
    }
  }
}
