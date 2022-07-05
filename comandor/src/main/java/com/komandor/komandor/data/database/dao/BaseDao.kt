package com.komandor.komandor.data.database.dao

import androidx.room.*
// https://gist.github.com/florina-muntenescu/1c78858f286d196d545c038a71a3e864
@Dao
abstract class BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(items: List<T>):List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: T): Long

    @Update
    abstract fun update(item: T)

    @Delete
    abstract fun delete(item: T)


}