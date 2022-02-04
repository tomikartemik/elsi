package com.komandor.komandor.data.database.certificates;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public final class CertificateDao_Impl implements CertificateDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCertificate;

  public CertificateDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCertificate = new EntityInsertionAdapter<Certificate>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `CERTIFICATES`(`cert_id`,`contact_id`,`is_local`,`is_active`,`base64`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Certificate value) {
        stmt.bindLong(1, value.getCid());
        stmt.bindLong(2, value.getPid());
        final int _tmp;
        _tmp = value.isLocal() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final int _tmp_1;
        _tmp_1 = value.isActive() ? 1 : 0;
        stmt.bindLong(4, _tmp_1);
        if (value.getBase64() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getBase64());
        }
      }
    };
  }

  @Override
  public void insert(final Certificate certificate) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCertificate.insert(certificate);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Flowable<Certificate> getActiveCertificate() {
    final String _sql = "select * from CERTIFICATES where is_active = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"CERTIFICATES"}, new Callable<Certificate>() {
      @Override
      public Certificate call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfCid = CursorUtil.getColumnIndexOrThrow(_cursor, "cert_id");
          final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "contact_id");
          final int _cursorIndexOfIsLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "is_local");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final int _cursorIndexOfBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "base64");
          final Certificate _result;
          if(_cursor.moveToFirst()) {
            _result = new Certificate();
            final int _tmpCid;
            _tmpCid = _cursor.getInt(_cursorIndexOfCid);
            _result.setCid(_tmpCid);
            final int _tmpPid;
            _tmpPid = _cursor.getInt(_cursorIndexOfPid);
            _result.setPid(_tmpPid);
            final boolean _tmpIsLocal;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLocal);
            _tmpIsLocal = _tmp != 0;
            _result.setLocal(_tmpIsLocal);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _result.setActive(_tmpIsActive);
            final String _tmpBase64;
            _tmpBase64 = _cursor.getString(_cursorIndexOfBase64);
            _result.setBase64(_tmpBase64);
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
  public Flowable<List<Certificate>> getLocalCertificates() {
    final String _sql = "select * from CERTIFICATES where is_local = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"CERTIFICATES"}, new Callable<List<Certificate>>() {
      @Override
      public List<Certificate> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfCid = CursorUtil.getColumnIndexOrThrow(_cursor, "cert_id");
          final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "contact_id");
          final int _cursorIndexOfIsLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "is_local");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final int _cursorIndexOfBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "base64");
          final List<Certificate> _result = new ArrayList<Certificate>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Certificate _item;
            _item = new Certificate();
            final int _tmpCid;
            _tmpCid = _cursor.getInt(_cursorIndexOfCid);
            _item.setCid(_tmpCid);
            final int _tmpPid;
            _tmpPid = _cursor.getInt(_cursorIndexOfPid);
            _item.setPid(_tmpPid);
            final boolean _tmpIsLocal;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLocal);
            _tmpIsLocal = _tmp != 0;
            _item.setLocal(_tmpIsLocal);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _item.setActive(_tmpIsActive);
            final String _tmpBase64;
            _tmpBase64 = _cursor.getString(_cursorIndexOfBase64);
            _item.setBase64(_tmpBase64);
            _result.add(_item);
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
  public Flowable<Certificate> getCertificateByID(final int cid) {
    final String _sql = "select * from CERTIFICATES where cert_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cid);
    return RxRoom.createFlowable(__db, new String[]{"CERTIFICATES"}, new Callable<Certificate>() {
      @Override
      public Certificate call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfCid = CursorUtil.getColumnIndexOrThrow(_cursor, "cert_id");
          final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "contact_id");
          final int _cursorIndexOfIsLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "is_local");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final int _cursorIndexOfBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "base64");
          final Certificate _result;
          if(_cursor.moveToFirst()) {
            _result = new Certificate();
            final int _tmpCid;
            _tmpCid = _cursor.getInt(_cursorIndexOfCid);
            _result.setCid(_tmpCid);
            final int _tmpPid;
            _tmpPid = _cursor.getInt(_cursorIndexOfPid);
            _result.setPid(_tmpPid);
            final boolean _tmpIsLocal;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLocal);
            _tmpIsLocal = _tmp != 0;
            _result.setLocal(_tmpIsLocal);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _result.setActive(_tmpIsActive);
            final String _tmpBase64;
            _tmpBase64 = _cursor.getString(_cursorIndexOfBase64);
            _result.setBase64(_tmpBase64);
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
  public String getCertificateStringByIDSync(final int cid) {
    final String _sql = "select base64 from CERTIFICATES where cert_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, cid);
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getString(0);
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
  public Flowable<List<Certificate>> getCertificatesByContactID(final int contactID) {
    final String _sql = "select * from CERTIFICATES where contact_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, contactID);
    return RxRoom.createFlowable(__db, new String[]{"CERTIFICATES"}, new Callable<List<Certificate>>() {
      @Override
      public List<Certificate> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfCid = CursorUtil.getColumnIndexOrThrow(_cursor, "cert_id");
          final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "contact_id");
          final int _cursorIndexOfIsLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "is_local");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "is_active");
          final int _cursorIndexOfBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "base64");
          final List<Certificate> _result = new ArrayList<Certificate>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Certificate _item;
            _item = new Certificate();
            final int _tmpCid;
            _tmpCid = _cursor.getInt(_cursorIndexOfCid);
            _item.setCid(_tmpCid);
            final int _tmpPid;
            _tmpPid = _cursor.getInt(_cursorIndexOfPid);
            _item.setPid(_tmpPid);
            final boolean _tmpIsLocal;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsLocal);
            _tmpIsLocal = _tmp != 0;
            _item.setLocal(_tmpIsLocal);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            _item.setActive(_tmpIsActive);
            final String _tmpBase64;
            _tmpBase64 = _cursor.getString(_cursorIndexOfBase64);
            _item.setBase64(_tmpBase64);
            _result.add(_item);
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
}
