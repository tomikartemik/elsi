package com.komandor.komandor.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.komandor.komandor.data.database.model.LocalCertificate

@Dao
abstract class LocalCertificateDao: BaseDao<LocalCertificate>() {

    @Query("select * from LocalCertificate where key = :key")
    abstract fun getByKey(key:String): LocalCertificate?

    @Query("select * from LocalCertificate where current = :current")
    abstract fun getCurrent(current:Boolean = true): LocalCertificate?

    @Query("select * from LocalCertificate")
    abstract fun getAll(): List<LocalCertificate>
}