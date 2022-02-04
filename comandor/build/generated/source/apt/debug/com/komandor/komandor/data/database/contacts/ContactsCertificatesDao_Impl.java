package com.komandor.komandor.data.database.contacts;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.komandor.komandor.data.database.certificates.Certificate;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.List;

@SuppressWarnings("unchecked")
public final class ContactsCertificatesDao_Impl extends ContactsCertificatesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfContact;

  private final EntityInsertionAdapter __insertionAdapterOfCertificate;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfContact;

  public ContactsCertificatesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContact = new EntityInsertionAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `CONTACTS`(`id`,`type`,`pid`,`local_id`,`uid`,`contact_name`,`phone`,`unmasked_phone`,`full_name`,`surname`,`name`,`patronymic`,`company`,`title`,`photo`,`chat_id`,`is_updated`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getType());
        stmt.bindLong(3, value.getPid());
        stmt.bindLong(4, value.getLocalID());
        stmt.bindLong(5, value.getUid());
        if (value.getContactName() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getContactName());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getPhone());
        }
        if (value.getUnmaskedPhone() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getUnmaskedPhone());
        }
        if (value.getFullName() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getFullName());
        }
        if (value.getSurname() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getSurname());
        }
        if (value.getName() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getName());
        }
        if (value.getPatronymic() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getPatronymic());
        }
        if (value.getCompany() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getCompany());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getTitle());
        }
        if (value.getPhoto() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getPhoto());
        }
        if (value.getChatID() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getChatID());
        }
        final int _tmp;
        _tmp = value.isUpdated() ? 1 : 0;
        stmt.bindLong(17, _tmp);
      }
    };
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
    this.__updateAdapterOfContact = new EntityDeletionOrUpdateAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `CONTACTS` SET `id` = ?,`type` = ?,`pid` = ?,`local_id` = ?,`uid` = ?,`contact_name` = ?,`phone` = ?,`unmasked_phone` = ?,`full_name` = ?,`surname` = ?,`name` = ?,`patronymic` = ?,`company` = ?,`title` = ?,`photo` = ?,`chat_id` = ?,`is_updated` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getType());
        stmt.bindLong(3, value.getPid());
        stmt.bindLong(4, value.getLocalID());
        stmt.bindLong(5, value.getUid());
        if (value.getContactName() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getContactName());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getPhone());
        }
        if (value.getUnmaskedPhone() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getUnmaskedPhone());
        }
        if (value.getFullName() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getFullName());
        }
        if (value.getSurname() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getSurname());
        }
        if (value.getName() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getName());
        }
        if (value.getPatronymic() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getPatronymic());
        }
        if (value.getCompany() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getCompany());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getTitle());
        }
        if (value.getPhoto() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindString(15, value.getPhoto());
        }
        if (value.getChatID() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindString(16, value.getChatID());
        }
        final int _tmp;
        _tmp = value.isUpdated() ? 1 : 0;
        stmt.bindLong(17, _tmp);
        stmt.bindLong(18, value.getId());
      }
    };
  }

  @Override
  public void insert(final Contact contact) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfContact.insert(contact);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertCertificates(final List<Certificate> certificates) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCertificate.insert(certificates);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Contact contact) {
    __db.beginTransaction();
    try {
      __updateAdapterOfContact.handle(contact);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateContact(final Contact dbContact, final Contact contact) {
    __db.beginTransaction();
    try {
      super.updateContact(dbContact, contact);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertContact(final Contact contact) {
    __db.beginTransaction();
    try {
      super.insertContact(contact);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertContactAndCertificates(final Contact contact,
      final List<Certificate> certificates) {
    __db.beginTransaction();
    try {
      super.insertContactAndCertificates(contact, certificates);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Contact getContactByPID(final int pid) {
    final String _sql = "select * from contacts where pid = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, pid);
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "pid");
      final int _cursorIndexOfLocalID = CursorUtil.getColumnIndexOrThrow(_cursor, "local_id");
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfContactName = CursorUtil.getColumnIndexOrThrow(_cursor, "contact_name");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
      final int _cursorIndexOfUnmaskedPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "unmasked_phone");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "full_name");
      final int _cursorIndexOfSurname = CursorUtil.getColumnIndexOrThrow(_cursor, "surname");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPatronymic = CursorUtil.getColumnIndexOrThrow(_cursor, "patronymic");
      final int _cursorIndexOfCompany = CursorUtil.getColumnIndexOrThrow(_cursor, "company");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPhoto = CursorUtil.getColumnIndexOrThrow(_cursor, "photo");
      final int _cursorIndexOfChatID = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_id");
      final int _cursorIndexOfIsUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "is_updated");
      final Contact _result;
      if(_cursor.moveToFirst()) {
        _result = new Contact();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        _result.setType(_tmpType);
        final int _tmpPid;
        _tmpPid = _cursor.getInt(_cursorIndexOfPid);
        _result.setPid(_tmpPid);
        final int _tmpLocalID;
        _tmpLocalID = _cursor.getInt(_cursorIndexOfLocalID);
        _result.setLocalID(_tmpLocalID);
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _result.setUid(_tmpUid);
        final String _tmpContactName;
        _tmpContactName = _cursor.getString(_cursorIndexOfContactName);
        _result.setContactName(_tmpContactName);
        final String _tmpPhone;
        _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        _result.setPhone(_tmpPhone);
        final String _tmpUnmaskedPhone;
        _tmpUnmaskedPhone = _cursor.getString(_cursorIndexOfUnmaskedPhone);
        _result.setUnmaskedPhone(_tmpUnmaskedPhone);
        final String _tmpFullName;
        _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
        _result.setFullName(_tmpFullName);
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
        final String _tmpPhoto;
        _tmpPhoto = _cursor.getString(_cursorIndexOfPhoto);
        _result.setPhoto(_tmpPhoto);
        final String _tmpChatID;
        _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
        _result.setChatID(_tmpChatID);
        final boolean _tmpIsUpdated;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsUpdated);
        _tmpIsUpdated = _tmp != 0;
        _result.setUpdated(_tmpIsUpdated);
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
  public Contact getContactByPhone(final String phone) {
    final String _sql = "select * from contacts where unmasked_phone = ? and pid = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (phone == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, phone);
    }
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "pid");
      final int _cursorIndexOfLocalID = CursorUtil.getColumnIndexOrThrow(_cursor, "local_id");
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfContactName = CursorUtil.getColumnIndexOrThrow(_cursor, "contact_name");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
      final int _cursorIndexOfUnmaskedPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "unmasked_phone");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "full_name");
      final int _cursorIndexOfSurname = CursorUtil.getColumnIndexOrThrow(_cursor, "surname");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfPatronymic = CursorUtil.getColumnIndexOrThrow(_cursor, "patronymic");
      final int _cursorIndexOfCompany = CursorUtil.getColumnIndexOrThrow(_cursor, "company");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfPhoto = CursorUtil.getColumnIndexOrThrow(_cursor, "photo");
      final int _cursorIndexOfChatID = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_id");
      final int _cursorIndexOfIsUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "is_updated");
      final Contact _result;
      if(_cursor.moveToFirst()) {
        _result = new Contact();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        _result.setType(_tmpType);
        final int _tmpPid;
        _tmpPid = _cursor.getInt(_cursorIndexOfPid);
        _result.setPid(_tmpPid);
        final int _tmpLocalID;
        _tmpLocalID = _cursor.getInt(_cursorIndexOfLocalID);
        _result.setLocalID(_tmpLocalID);
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        _result.setUid(_tmpUid);
        final String _tmpContactName;
        _tmpContactName = _cursor.getString(_cursorIndexOfContactName);
        _result.setContactName(_tmpContactName);
        final String _tmpPhone;
        _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        _result.setPhone(_tmpPhone);
        final String _tmpUnmaskedPhone;
        _tmpUnmaskedPhone = _cursor.getString(_cursorIndexOfUnmaskedPhone);
        _result.setUnmaskedPhone(_tmpUnmaskedPhone);
        final String _tmpFullName;
        _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
        _result.setFullName(_tmpFullName);
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
        final String _tmpPhoto;
        _tmpPhoto = _cursor.getString(_cursorIndexOfPhoto);
        _result.setPhoto(_tmpPhoto);
        final String _tmpChatID;
        _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
        _result.setChatID(_tmpChatID);
        final boolean _tmpIsUpdated;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsUpdated);
        _tmpIsUpdated = _tmp != 0;
        _result.setUpdated(_tmpIsUpdated);
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
