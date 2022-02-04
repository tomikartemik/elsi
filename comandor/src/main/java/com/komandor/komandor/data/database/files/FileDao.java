package com.komandor.komandor.data.database.files;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import io.reactivex.Flowable;

@Dao
public abstract class FileDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract long insert(File file);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  public abstract void update(File file);
  
  @Query("select * from " + File.TABLE_NAME + " where id = :id")
  public abstract Flowable<File> getFileByID(long id);
  
  @Query("select * from " + File.TABLE_NAME + " where file_id = :fileID")
  public abstract File getFileByFileIDSync(String fileID);

  @Query("select * from " + File.TABLE_NAME + " where file_path = :filePath")
  public abstract File getFileByFilePath(String filePath);
  
  @Query("update " + File.TABLE_NAME + " set file_id = :fileID where id = :localFileID")
  public abstract void updateFileID(long localFileID, String fileID);
  
  @Query("update " + File.TABLE_NAME + " set file_path = :filePath where file_id = :fileID")
  public abstract void updateFilePathByFileID(String fileID, String filePath);
  
  @Query("update " + File.TABLE_NAME + " set file_path = :filePath where id = :id")
  public abstract void updateFilePathByID(long id, String filePath);

  @Transaction
  public long insertFile(File file) {
    File dbFile = getFileByFileIDSync(file.getFileID());

    if (dbFile == null && file.getFilePath() != null && !file.getFilePath().equals("")) {
      dbFile = getFileByFilePath(file.getFilePath());
    }

    if (dbFile != null) {
      file.setId(dbFile.getId());
      file.setFilePath(dbFile.getFilePath());
      update(file);
      return file.getId();
    }

    return insert(file);
  }

}
