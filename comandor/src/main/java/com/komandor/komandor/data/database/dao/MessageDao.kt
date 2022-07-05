package com.komandor.komandor.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.komandor.komandor.data.database.model.Message
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MessageDao: BaseDao<Message>() {

    @Query("select * from message_table where messageId = :messageId")
    abstract fun getById(messageId: String): Message?

    @Query("select * from message_table")
    abstract fun getAll(): Flow<List<Message>>

}