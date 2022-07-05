package com.komandor.komandor.data.database.dao

import androidx.room.*
import com.komandor.komandor.data.database.model.File
import kotlinx.coroutines.flow.Flow

abstract class FileDao: BaseDao<File>() {


    @Query("select * from FILES where id = :id")
    abstract fun getFileByID(id: Long): Flow<File?>?

    @Query("select * from FILES where file_id = :fileID")
    abstract fun getFileByFileIDSync(fileID: Long?): File?

    @Query("select * from FILES where file_path = :filePath")
    abstract fun getFileByFilePath(filePath: String?): File?

    @Query("update FILES set file_id = :fileID where id = :localFileID")
    abstract fun updateFileID(localFileID: Long, fileID: Long?)

    @Query("update FILES set file_path = :filePath where file_id = :fileID")
    abstract fun updateFilePathByFileID(fileID: String?, filePath: String?)

    @Query("update FILES set file_path = :filePath where id = :id")
    abstract fun updateFilePathByID(id: Long, filePath: String?)

    @Transaction
    open fun insertFile(file: File): Long {
        var dbFile = getFileByFileIDSync(file.fileID)
        if (dbFile == null && file.filePath != null && file.filePath != "") {
            dbFile = getFileByFilePath(file.filePath)
        }
        if (dbFile != null) {
            file.id = dbFile.id
            file.filePath = dbFile.filePath
            update(file)
            return file.id
        }
        return insert(file)
    }
}