package com.komandor.komandor.data.database.users;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUser;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ACCOUNTS`(`user_id`,`cert_id`,`type`,`surname`,`name`,`patronymic`,`company`,`title`,`INN`,`OGRN`,`SNILS`,`phone`,`photo`,`email`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        stmt.bindLong(1, value.getPid());
        stmt.bindLong(2, value.getCid());
        stmt.bindLong(3, value.getType());
        if (value.getSurname() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSurname());
        }
        if (value.getName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getName());
        }
        if (value.getPatronymic() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPatronymic());
        }
        if (value.getCompany() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getCompany());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getTitle());
        }
        if (value.getInn() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getInn());
        }
        if (value.getOgrn() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getOgrn());
        }
        if (value.getSnils() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getSnils());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getPhone());
        }
        if (value.getPhoto() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getPhoto());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getEmail());
        }
      }
    };
  }

  @Override
  public void insert(final User user) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Flowable<User> getUserByPID(final int userPID) {
    final String _sql = "select * from ACCOUNTS where user_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userPID);
    return RxRoom.createFlowable(__db, new String[]{"ACCOUNTS"}, new Callable<User>() {
      @Override
      public User call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfCid = CursorUtil.getColumnIndexOrThrow(_cursor, "cert_id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfSurname = CursorUtil.getColumnIndexOrThrow(_cursor, "surname");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPatronymic = CursorUtil.getColumnIndexOrThrow(_cursor, "patronymic");
          final int _cursorIndexOfCompany = CursorUtil.getColumnIndexOrThrow(_cursor, "company");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfInn = CursorUtil.getColumnIndexOrThrow(_cursor, "INN");
          final int _cursorIndexOfOgrn = CursorUtil.getColumnIndexOrThrow(_cursor, "OGRN");
          final int _cursorIndexOfSnils = CursorUtil.getColumnIndexOrThrow(_cursor, "SNILS");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfPhoto = CursorUtil.getColumnIndexOrThrow(_cursor, "photo");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final User _result;
          if(_cursor.moveToFirst()) {
            _result = new User();
            final int _tmpPid;
            _tmpPid = _cursor.getInt(_cursorIndexOfPid);
            _result.setPid(_tmpPid);
            final int _tmpCid;
            _tmpCid = _cursor.getInt(_cursorIndexOfCid);
            _result.setCid(_tmpCid);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            _result.setType(_tmpType);
            final String _tmpSurname;
            _tmpSurname = _cursor.getString(_cursorIndexOfSurname);
            _result.setSurname(_tmpSurname);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _result.setName(_tmpName);
            final String _tmpPatronymic;
            _tmpPatronymic = _cursor.getString(_cursorIndexOfPatronymic);
            _result.setPatronymic(_tmpPatronymic);
            final String _tmpCompany;
            _tmpCompany = _cursor.getString(_cursorIndexOfCompany);
            _result.setCompany(_tmpCompany);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _result.setTitle(_tmpTitle);
            final String _tmpInn;
            _tmpInn = _cursor.getString(_cursorIndexOfInn);
            _result.setInn(_tmpInn);
            final String _tmpOgrn;
            _tmpOgrn = _cursor.getString(_cursorIndexOfOgrn);
            _result.setOgrn(_tmpOgrn);
            final String _tmpSnils;
            _tmpSnils = _cursor.getString(_cursorIndexOfSnils);
            _result.setSnils(_tmpSnils);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            _result.setPhone(_tmpPhone);
            final String _tmpPhoto;
            _tmpPhoto = _cursor.getString(_cursorIndexOfPhoto);
            _result.setPhoto(_tmpPhoto);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            _result.setEmail(_tmpEmail);
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
  public Maybe<User> getActiveUser() {
    final String _sql = "select * from ACCOUNTS limit 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return Maybe.fromCallable(new Callable<User>() {
      @Override
      public User call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfPid = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfCid = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "cert_id");
          final int _cursorIndexOfType = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfSurname = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "surname");
          final int _cursorIndexOfName = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPatronymic = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "patronymic");
          final int _cursorIndexOfCompany = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "company");
          final int _cursorIndexOfTitle = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfInn = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "INN");
          final int _cursorIndexOfOgrn = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "OGRN");
          final int _cursorIndexOfSnils = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "SNILS");
          final int _cursorIndexOfPhone = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfPhoto = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "photo");
          final int _cursorIndexOfEmail = androidx.room.util.CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final com.komandor.komandor.data.database.users.User _result;
          if(_cursor.moveToFirst()) {
            _result = new com.komandor.komandor.data.database.users.User();
            final int _tmpPid;
            _tmpPid = _cursor.getInt(_cursorIndexOfPid);
            _result.setPid(_tmpPid);
            final int _tmpCid;
            _tmpCid = _cursor.getInt(_cursorIndexOfCid);
            _result.setCid(_tmpCid);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            _result.setType(_tmpType);
            final java.lang.String _tmpSurname;
            _tmpSurname = _cursor.getString(_cursorIndexOfSurname);
            _result.setSurname(_tmpSurname);
            final java.lang.String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _result.setName(_tmpName);
            final java.lang.String _tmpPatronymic;
            _tmpPatronymic = _cursor.getString(_cursorIndexOfPatronymic);
            _result.setPatronymic(_tmpPatronymic);
            final java.lang.String _tmpCompany;
            _tmpCompany = _cursor.getString(_cursorIndexOfCompany);
            _result.setCompany(_tmpCompany);
            final java.lang.String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _result.setTitle(_tmpTitle);
            final java.lang.String _tmpInn;
            _tmpInn = _cursor.getString(_cursorIndexOfInn);
            _result.setInn(_tmpInn);
            final java.lang.String _tmpOgrn;
            _tmpOgrn = _cursor.getString(_cursorIndexOfOgrn);
            _result.setOgrn(_tmpOgrn);
            final java.lang.String _tmpSnils;
            _tmpSnils = _cursor.getString(_cursorIndexOfSnils);
            _result.setSnils(_tmpSnils);
            final java.lang.String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            _result.setPhone(_tmpPhone);
            final java.lang.String _tmpPhoto;
            _tmpPhoto = _cursor.getString(_cursorIndexOfPhoto);
            _result.setPhoto(_tmpPhoto);
            final java.lang.String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            _result.setEmail(_tmpEmail);
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
}
