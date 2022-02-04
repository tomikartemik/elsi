package com.komandor.komandor.data.database.files;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public final class FileDao_Impl extends FileDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfFile;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfFile;

  private final SharedSQLiteStatement __preparedStmtOfUpdateFileID;

  private final SharedSQLiteStatement __preparedStmtOfUpdateFilePathByFileID;

  private final SharedSQLiteStatement __preparedStmtOfUpdateFilePathByID;

  public FileDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFile = new EntityInsertionAdapter<File>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `FILES`(`id`,`type`,`name`,`name_sign`,`file_id`,`cms`,`file_path`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, File value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getType());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getNameSign() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNameSign());
        }
        if (value.getFileID() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getFileID());
        }
        if (value.getCms() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCms());
        }
        if (value.getFilePath() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getFilePath());
        }
      }
    };
    this.__updateAdapterOfFile = new EntityDeletionOrUpdateAdapter<File>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `FILES` SET `id` = ?,`type` = ?,`name` = ?,`name_sign` = ?,`file_id` = ?,`cms` = ?,`file_path` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, File value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getType());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getNameSign() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNameSign());
        }
        if (value.getFileID() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getFileID());
        }
        if (value.getCms() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCms());
        }
        if (value.getFilePath() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getFilePath());
        }
        stmt.bindLong(8, value.getId());
      }
    };
    this.__preparedStmtOfUpdateFileID = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update FILES set file_id = ? where id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateFilePathByFileID = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update FILES set file_path = ? where file_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateFilePathByID = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update FILES set file_path = ? where id = ?";
        return _query;
      }
    };
  }

  @Override
  public long insert(final File file) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfFile.insertAndReturnId(file);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final File file) {
    __db.beginTransaction();
    try {
      __updateAdapterOfFile.handle(file);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long insertFile(final File file) {
    __db.beginTransaction();
    try {
      long _result = super.insertFile(file);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateFileID(final long localFileID, final String fileID) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateFileID.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (fileID == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, fileID);
      }
      _argIndex = 2;
      _stmt.bindLong(_argIndex, localFileID);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateFileID.release(_stmt);
    }
  }

  @Override
  public void updateFilePathByFileID(final String fileID, final String filePath) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateFilePathByFileID.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (filePath == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, filePath);
      }
      _argIndex = 2;
      if (fileID == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, fileID);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateFilePathByFileID.release(_stmt);
    }
  }

  @Override
  public void updateFilePathByID(final long id, final String filePath) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateFilePathByID.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (filePath == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, filePath);
      }
      _argIndex = 2;
      _stmt.bindLong(_argIndex, id);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateFilePathByID.release(_stmt);
    }
  }

  @Override
  public Flowable<File> getFileByID(final long id) {
    final String _sql = "select * from FILES where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return RxRoom.createFlowable(__db, new String[]{"FILES"}, new Callable<File>() {
      @Override
      public File call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameSign = CursorUtil.getColumnIndexOrThrow(_cursor, "name_sign");
          final int _cursorIndexOfFileID = CursorUtil.getColumnIndexOrThrow(_cursor, "file_id");
          final int _cursorIndexOfCms = CursorUtil.getColumnIndexOrThrow(_cursor, "cms");
          final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "file_path");
          final File _result;
          if(_cursor.moveToFirst()) {
            _result = new File();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _result.setId(_tmpId);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            _result.setType(_tmpType);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _result.setName(_tmpName);
            final String _tmpNameSign;
            _tmpNameSign = _cursor.getString(_cursorIndexOfNameSign);
            _result.setNameSign(_tmpNameSign);
            final String _tmpFileID;
            _tmpFileID = _cursor.getString(_cursorIndexOfFileID);
            _result.setFileID(_tmpFileID);
            final String _tmpCms;
            _tmpCms = _cursor.getString(_cursorIndexOfCms);
            _result.setCms(_tmpCms);
            final String _tmpFilePath;
            _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
            _result.setFilePath(_tmpFilePath);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public File getFileByFileIDSync(final String fileID) {
    final String _sql = "select * from FILES where file_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (fileID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, fileID);
    }
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfNameSign = CursorUtil.getColumnIndexOrThrow(_cursor, "name_sign");
      final int _cursorIndexOfFileID = CursorUtil.getColumnIndexOrThrow(_cursor, "file_id");
      final int _cursorIndexOfCms = CursorUtil.getColumnIndexOrThrow(_cursor, "cms");
      final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "file_path");
      final File _result;
      if(_cursor.moveToFirst()) {
        _result = new File();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        _result.setType(_tmpType);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
        final String _tmpNameSign;
        _tmpNameSign = _cursor.getString(_cursorIndexOfNameSign);
        _result.setNameSign(_tmpNameSign);
        final String _tmpFileID;
        _tmpFileID = _cursor.getString(_cursorIndexOfFileID);
        _result.setFileID(_tmpFileID);
        final String _tmpCms;
        _tmpCms = _cursor.getString(_cursorIndexOfCms);
        _result.setCms(_tmpCms);
        final String _tmpFilePath;
        _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
        _result.setFilePath(_tmpFilePath);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public File getFileByFilePath(final String filePath) {
    final String _sql = "select * from FILES where file_path = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (filePath == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, filePath);
    }
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfNameSign = CursorUtil.getColumnIndexOrThrow(_cursor, "name_sign");
      final int _cursorIndexOfFileID = CursorUtil.getColumnIndexOrThrow(_cursor, "file_id");
      final int _cursorIndexOfCms = CursorUtil.getColumnIndexOrThrow(_cursor, "cms");
      final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "file_path");
      final File _result;
      if(_cursor.moveToFirst()) {
        _result = new File();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        _result.setType(_tmpType);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
        final String _tmpNameSign;
        _tmpNameSign = _cursor.getString(_cursorIndexOfNameSign);
        _result.setNameSign(_tmpNameSign);
        final String _tmpFileID;
        _tmpFileID = _cursor.getString(_cursorIndexOfFileID);
        _result.setFileID(_tmpFileID);
        final String _tmpCms;
        _tmpCms = _cursor.getString(_cursorIndexOfCms);
        _result.setCms(_tmpCms);
        final String _tmpFilePath;
        _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
        _result.setFilePath(_tmpFilePath);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
