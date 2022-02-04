package com.komandor.komandor.data.database.contacts;

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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public final class ContactDao_Impl extends ContactDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfContact;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfContact;

  private final SharedSQLiteStatement __preparedStmtOfSetLocalContactsOld;

  private final SharedSQLiteStatement __preparedStmtOfSetContactsOld;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldContacts;

  public ContactDao_Impl(RoomDatabase __db) {
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
    this.__preparedStmtOfSetLocalContactsOld = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update CONTACTS set is_updated = 0 where pid = 0";
        return _query;
      }
    };
    this.__preparedStmtOfSetContactsOld = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update CONTACTS set is_updated = 0 where pid != 0";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOldContacts = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from CONTACTS where is_updated = 0";
        return _query;
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
  public void insertLocalContact(final Contact contact) {
    __db.beginTransaction();
    try {
      super.insertLocalContact(contact);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void setLocalContactsOld() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetLocalContactsOld.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetLocalContactsOld.release(_stmt);
    }
  }

  @Override
  public void setContactsOld() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetContactsOld.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetContactsOld.release(_stmt);
    }
  }

  @Override
  public void deleteOldContacts() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldContacts.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteOldContacts.release(_stmt);
    }
  }

  @Override
  public Flowable<List<Contact>> getContacts() {
    final String _sql = "select * from CONTACTS order by name asc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"CONTACTS"}, new Callable<List<Contact>>() {
      @Override
      public List<Contact> call() throws Exception {
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
          final List<Contact> _result = new ArrayList<Contact>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Contact _item;
            _item = new Contact();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            _item.setType(_tmpType);
            final int _tmpPid;
            _tmpPid = _cursor.getInt(_cursorIndexOfPid);
            _item.setPid(_tmpPid);
            final int _tmpLocalID;
            _tmpLocalID = _cursor.getInt(_cursorIndexOfLocalID);
            _item.setLocalID(_tmpLocalID);
            final int _tmpUid;
            _tmpUid = _cursor.getInt(_cursorIndexOfUid);
            _item.setUid(_tmpUid);
            final String _tmpContactName;
            _tmpContactName = _cursor.getString(_cursorIndexOfContactName);
            _item.setContactName(_tmpContactName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            _item.setPhone(_tmpPhone);
            final String _tmpUnmaskedPhone;
            _tmpUnmaskedPhone = _cursor.getString(_cursorIndexOfUnmaskedPhone);
            _item.setUnmaskedPhone(_tmpUnmaskedPhone);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            _item.setFullName(_tmpFullName);
            final String _tmpSurname;
            _tmpSurname = _cursor.getString(_cursorIndexOfSurname);
            _item.setSurname(_tmpSurname);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final String _tmpPatronymic;
            _tmpPatronymic = _cursor.getString(_cursorIndexOfPatronymic);
            _item.setPatronymic(_tmpPatronymic);
            final String _tmpCompany;
            _tmpCompany = _cursor.getString(_cursorIndexOfCompany);
            _item.setCompany(_tmpCompany);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpPhoto;
            _tmpPhoto = _cursor.getString(_cursorIndexOfPhoto);
            _item.setPhoto(_tmpPhoto);
            final String _tmpChatID;
            _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
            _item.setChatID(_tmpChatID);
            final boolean _tmpIsUpdated;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsUpdated);
            _tmpIsUpdated = _tmp != 0;
            _item.setUpdated(_tmpIsUpdated);
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
  public Flowable<List<Contact>> getSortedContacts() {
    final String _sql = "select * from (select * from (select * from CONTACTS where pid != 0 order by full_name asc) union all select * from (select * from CONTACTS where pid = 0 order by contact_name asc))";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"CONTACTS"}, new Callable<List<Contact>>() {
      @Override
      public List<Contact> call() throws Exception {
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
          final List<Contact> _result = new ArrayList<Contact>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Contact _item;
            _item = new Contact();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            _item.setType(_tmpType);
            final int _tmpPid;
            _tmpPid = _cursor.getInt(_cursorIndexOfPid);
            _item.setPid(_tmpPid);
            final int _tmpLocalID;
            _tmpLocalID = _cursor.getInt(_cursorIndexOfLocalID);
            _item.setLocalID(_tmpLocalID);
            final int _tmpUid;
            _tmpUid = _cursor.getInt(_cursorIndexOfUid);
            _item.setUid(_tmpUid);
            final String _tmpContactName;
            _tmpContactName = _cursor.getString(_cursorIndexOfContactName);
            _item.setContactName(_tmpContactName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            _item.setPhone(_tmpPhone);
            final String _tmpUnmaskedPhone;
            _tmpUnmaskedPhone = _cursor.getString(_cursorIndexOfUnmaskedPhone);
            _item.setUnmaskedPhone(_tmpUnmaskedPhone);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            _item.setFullName(_tmpFullName);
            final String _tmpSurname;
            _tmpSurname = _cursor.getString(_cursorIndexOfSurname);
            _item.setSurname(_tmpSurname);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final String _tmpPatronymic;
            _tmpPatronymic = _cursor.getString(_cursorIndexOfPatronymic);
            _item.setPatronymic(_tmpPatronymic);
            final String _tmpCompany;
            _tmpCompany = _cursor.getString(_cursorIndexOfCompany);
            _item.setCompany(_tmpCompany);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpPhoto;
            _tmpPhoto = _cursor.getString(_cursorIndexOfPhoto);
            _item.setPhoto(_tmpPhoto);
            final String _tmpChatID;
            _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
            _item.setChatID(_tmpChatID);
            final boolean _tmpIsUpdated;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsUpdated);
            _tmpIsUpdated = _tmp != 0;
            _item.setUpdated(_tmpIsUpdated);
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
  public Flowable<List<Contact>> getContactsByChatID(final String chatID) {
    final String _sql = "select * from CONTACTS where chat_id like ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (chatID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, chatID);
    }
    return RxRoom.createFlowable(__db, new String[]{"CONTACTS"}, new Callable<List<Contact>>() {
      @Override
      public List<Contact> call() throws Exception {
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
          final List<Contact> _result = new ArrayList<Contact>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Contact _item;
            _item = new Contact();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            _item.setType(_tmpType);
            final int _tmpPid;
            _tmpPid = _cursor.getInt(_cursorIndexOfPid);
            _item.setPid(_tmpPid);
            final int _tmpLocalID;
            _tmpLocalID = _cursor.getInt(_cursorIndexOfLocalID);
            _item.setLocalID(_tmpLocalID);
            final int _tmpUid;
            _tmpUid = _cursor.getInt(_cursorIndexOfUid);
            _item.setUid(_tmpUid);
            final String _tmpContactName;
            _tmpContactName = _cursor.getString(_cursorIndexOfContactName);
            _item.setContactName(_tmpContactName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            _item.setPhone(_tmpPhone);
            final String _tmpUnmaskedPhone;
            _tmpUnmaskedPhone = _cursor.getString(_cursorIndexOfUnmaskedPhone);
            _item.setUnmaskedPhone(_tmpUnmaskedPhone);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            _item.setFullName(_tmpFullName);
            final String _tmpSurname;
            _tmpSurname = _cursor.getString(_cursorIndexOfSurname);
            _item.setSurname(_tmpSurname);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            final String _tmpPatronymic;
            _tmpPatronymic = _cursor.getString(_cursorIndexOfPatronymic);
            _item.setPatronymic(_tmpPatronymic);
            final String _tmpCompany;
            _tmpCompany = _cursor.getString(_cursorIndexOfCompany);
            _item.setCompany(_tmpCompany);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpPhoto;
            _tmpPhoto = _cursor.getString(_cursorIndexOfPhoto);
            _item.setPhoto(_tmpPhoto);
            final String _tmpChatID;
            _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
            _item.setChatID(_tmpChatID);
            final boolean _tmpIsUpdated;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsUpdated);
            _tmpIsUpdated = _tmp != 0;
            _item.setUpdated(_tmpIsUpdated);
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
  public Contact getContactByPID(final int pid) {
    final String _sql = "select * from CONTACTS where pid = ?";
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
  public Flowable<Contact> getContactByID(final int id) {
    final String _sql = "select * from CONTACTS where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return RxRoom.createFlowable(__db, new String[]{"CONTACTS"}, new Callable<Contact>() {
      @Override
      public Contact call() throws Exception {
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
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Contact getLocalContactByPhone(final String phone) {
    final String _sql = "select * from CONTACTS where unmasked_phone = ? and pid = 0";
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
