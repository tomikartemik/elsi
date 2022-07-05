package com.komandor.komandor.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.komandor.komandor.data.database.model.Chat
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ChatDao: BaseDao<Chat>() {
    @Query("select * from chat_table where id = :id")
    abstract fun getById(id: String): Chat?

    @Query("select * from chat_table")
    abstract fun getAll(): Flow<List<Chat>>
}