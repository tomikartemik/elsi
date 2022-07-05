package com.komandor.komandor.di

import com.komandor.komandor.data.database.KomandorDatabase

import javax.inject.Inject

class FileStorage @Inject constructor(
    val db: KomandorDatabase
)  {
/*
    fun insertFile(messageResponse: MessageResponse): Long {
        return db.fileDao().insertFile(messageResponse.toFile())
    }

    fun insertFile(file: File): Long {
        return db.fileDao().insertFile(file)
    }

    fun getFileByID(id: Long): Flow<File?>? {
        return db.fileDao().getFileByID(id)
    }

    fun updateFileID(localFileID: Long, fileID: Long) {
        db.fileDao().updateFileID(localFileID, fileID)
    }

    fun updateFilePathByFileID(fileID: String?, filePath: String) {
        db.fileDao().updateFilePathByFileID(fileID, filePath)
    }

    fun updateFilePathByID(id: Long, filePath: String) {
        db.fileDao().updateFilePathByID(id, filePath)
    }

 */
}