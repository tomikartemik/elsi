package com.komandor.komandor.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.komandor.komandor.data.database.model.User

@Dao
abstract class UserDao: BaseDao<User>() {

    @Query("select * from ACCOUNTS where pid = :userPID")
    abstract fun getUserByPID(userPID: Int): User?

    @Query("select * from ACCOUNTS where current = :current")
    abstract fun getCurrent(current:Boolean = true): User?

}