package com.komandor.komandor.data.database.users;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface UserDao {
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(User user);
  
  @Query("select * from " + User.TABLE_NAME + " where user_id = :userPID")
  Flowable<User> getUserByPID(int userPID);
  
  @Query("select * from " + User.TABLE_NAME + " limit 1")
  Maybe<User> getActiveUser();
}
