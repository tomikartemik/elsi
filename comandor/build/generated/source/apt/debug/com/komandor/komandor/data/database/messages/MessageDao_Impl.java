package com.komandor.komandor.data.database.messages;

import android.database.Cursor;
import androidx.collection.LongSparseArray;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.komandor.komandor.data.database.contacts.Contact;
import com.komandor.komandor.data.database.files.File;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public final class MessageDao_Impl extends MessageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMessage;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfMessage;

  public MessageDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessage = new EntityInsertionAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `MESSAGES`(`id`,`message_id`,`chat_id`,`pid`,`cid`,`user`,`data`,`sign`,`type`,`file_id`,`date`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getMessageID());
        if (value.getChatID() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getChatID());
        }
        stmt.bindLong(4, value.getPid());
        stmt.bindLong(5, value.getCid());
        if (value.getUserName() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getUserName());
        }
        if (value.getData() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getData());
        }
        if (value.getSign() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getSign());
        }
        if (value.getType() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getType());
        }
        stmt.bindLong(10, value.getFileID());
        stmt.bindLong(11, value.getDate());
      }
    };
    this.__updateAdapterOfMessage = new EntityDeletionOrUpdateAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `MESSAGES` SET `id` = ?,`message_id` = ?,`chat_id` = ?,`pid` = ?,`cid` = ?,`user` = ?,`data` = ?,`sign` = ?,`type` = ?,`file_id` = ?,`date` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getMessageID());
        if (value.getChatID() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getChatID());
        }
        stmt.bindLong(4, value.getPid());
        stmt.bindLong(5, value.getCid());
        if (value.getUserName() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getUserName());
        }
        if (value.getData() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getData());
        }
        if (value.getSign() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getSign());
        }
        if (value.getType() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getType());
        }
        stmt.bindLong(10, value.getFileID());
        stmt.bindLong(11, value.getDate());
        stmt.bindLong(12, value.getId());
      }
    };
  }

  @Override
  public long insert(final Message message) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfMessage.insertAndReturnId(message);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Message message) {
    __db.beginTransaction();
    try {
      __updateAdapterOfMessage.handle(message);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public long insertMessage(final Message message) {
    __db.beginTransaction();
    try {
      long _result = super.insertMessage(message);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertManyMessages(final List<Message> messages) {
    __db.beginTransaction();
    try {
      super.insertManyMessages(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateMessage(final Message message) {
    __db.beginTransaction();
    try {
      super.updateMessage(message);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Message getMessageByMessageIDSync(final int messageID) {
    final String _sql = "select * from MESSAGES where message_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, messageID);
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfMessageID = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id");
      final int _cursorIndexOfChatID = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_id");
      final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "pid");
      final int _cursorIndexOfCid = CursorUtil.getColumnIndexOrThrow(_cursor, "cid");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user");
      final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
      final int _cursorIndexOfSign = CursorUtil.getColumnIndexOrThrow(_cursor, "sign");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfFileID = CursorUtil.getColumnIndexOrThrow(_cursor, "file_id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final Message _result;
      if(_cursor.moveToFirst()) {
        _result = new Message();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpMessageID;
        _tmpMessageID = _cursor.getInt(_cursorIndexOfMessageID);
        _result.setMessageID(_tmpMessageID);
        final String _tmpChatID;
        _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
        _result.setChatID(_tmpChatID);
        final int _tmpPid;
        _tmpPid = _cursor.getInt(_cursorIndexOfPid);
        _result.setPid(_tmpPid);
        final int _tmpCid;
        _tmpCid = _cursor.getInt(_cursorIndexOfCid);
        _result.setCid(_tmpCid);
        final String _tmpUserName;
        _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        _result.setUserName(_tmpUserName);
        final String _tmpData;
        _tmpData = _cursor.getString(_cursorIndexOfData);
        _result.setData(_tmpData);
        final String _tmpSign;
        _tmpSign = _cursor.getString(_cursorIndexOfSign);
        _result.setSign(_tmpSign);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _result.setType(_tmpType);
        final long _tmpFileID;
        _tmpFileID = _cursor.getLong(_cursorIndexOfFileID);
        _result.setFileID(_tmpFileID);
        final long _tmpDate;
        _tmpDate = _cursor.getLong(_cursorIndexOfDate);
        _result.setDate(_tmpDate);
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
  public Message getMessageByIDSync(final long id) {
    final String _sql = "select * from MESSAGES where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfMessageID = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id");
      final int _cursorIndexOfChatID = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_id");
      final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "pid");
      final int _cursorIndexOfCid = CursorUtil.getColumnIndexOrThrow(_cursor, "cid");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user");
      final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
      final int _cursorIndexOfSign = CursorUtil.getColumnIndexOrThrow(_cursor, "sign");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfFileID = CursorUtil.getColumnIndexOrThrow(_cursor, "file_id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final Message _result;
      if(_cursor.moveToFirst()) {
        _result = new Message();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
        final int _tmpMessageID;
        _tmpMessageID = _cursor.getInt(_cursorIndexOfMessageID);
        _result.setMessageID(_tmpMessageID);
        final String _tmpChatID;
        _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
        _result.setChatID(_tmpChatID);
        final int _tmpPid;
        _tmpPid = _cursor.getInt(_cursorIndexOfPid);
        _result.setPid(_tmpPid);
        final int _tmpCid;
        _tmpCid = _cursor.getInt(_cursorIndexOfCid);
        _result.setCid(_tmpCid);
        final String _tmpUserName;
        _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        _result.setUserName(_tmpUserName);
        final String _tmpData;
        _tmpData = _cursor.getString(_cursorIndexOfData);
        _result.setData(_tmpData);
        final String _tmpSign;
        _tmpSign = _cursor.getString(_cursorIndexOfSign);
        _result.setSign(_tmpSign);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _result.setType(_tmpType);
        final long _tmpFileID;
        _tmpFileID = _cursor.getLong(_cursorIndexOfFileID);
        _result.setFileID(_tmpFileID);
        final long _tmpDate;
        _tmpDate = _cursor.getLong(_cursorIndexOfDate);
        _result.setDate(_tmpDate);
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
  public Flowable<Message> getMessageByID(final long id) {
    final String _sql = "select * from MESSAGES where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return RxRoom.createFlowable(__db, new String[]{"MESSAGES"}, new Callable<Message>() {
      @Override
      public Message call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMessageID = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id");
          final int _cursorIndexOfChatID = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_id");
          final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "pid");
          final int _cursorIndexOfCid = CursorUtil.getColumnIndexOrThrow(_cursor, "cid");
          final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfSign = CursorUtil.getColumnIndexOrThrow(_cursor, "sign");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfFileID = CursorUtil.getColumnIndexOrThrow(_cursor, "file_id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final Message _result;
          if(_cursor.moveToFirst()) {
            _result = new Message();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _result.setId(_tmpId);
            final int _tmpMessageID;
            _tmpMessageID = _cursor.getInt(_cursorIndexOfMessageID);
            _result.setMessageID(_tmpMessageID);
            final String _tmpChatID;
            _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
            _result.setChatID(_tmpChatID);
            final int _tmpPid;
            _tmpPid = _cursor.getInt(_cursorIndexOfPid);
            _result.setPid(_tmpPid);
            final int _tmpCid;
            _tmpCid = _cursor.getInt(_cursorIndexOfCid);
            _result.setCid(_tmpCid);
            final String _tmpUserName;
            _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
            _result.setUserName(_tmpUserName);
            final String _tmpData;
            _tmpData = _cursor.getString(_cursorIndexOfData);
            _result.setData(_tmpData);
            final String _tmpSign;
            _tmpSign = _cursor.getString(_cursorIndexOfSign);
            _result.setSign(_tmpSign);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            _result.setType(_tmpType);
            final long _tmpFileID;
            _tmpFileID = _cursor.getLong(_cursorIndexOfFileID);
            _result.setFileID(_tmpFileID);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            _result.setDate(_tmpDate);
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
  public Flowable<MessageWithFileWithContact> getMessageWithFileWithContactByID(final long id) {
    final String _sql = "select * from MESSAGES where id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return RxRoom.createFlowable(__db, new String[]{"CONTACTS","FILES","MESSAGES"}, new Callable<MessageWithFileWithContact>() {
      @Override
      public MessageWithFileWithContact call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfMessageID = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id");
            final int _cursorIndexOfChatID = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_id");
            final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "pid");
            final int _cursorIndexOfCid = CursorUtil.getColumnIndexOrThrow(_cursor, "cid");
            final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user");
            final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
            final int _cursorIndexOfSign = CursorUtil.getColumnIndexOrThrow(_cursor, "sign");
            final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
            final int _cursorIndexOfFileID = CursorUtil.getColumnIndexOrThrow(_cursor, "file_id");
            final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
            final LongSparseArray<ArrayList<Contact>> _collectionContacts = new LongSparseArray<ArrayList<Contact>>();
            final LongSparseArray<ArrayList<File>> _collectionFiles = new LongSparseArray<ArrayList<File>>();
            while (_cursor.moveToNext()) {
              if (!_cursor.isNull(_cursorIndexOfPid)) {
                final long _tmpKey = _cursor.getLong(_cursorIndexOfPid);
                ArrayList<Contact> _tmpContactsCollection = _collectionContacts.get(_tmpKey);
                if (_tmpContactsCollection == null) {
                  _tmpContactsCollection = new ArrayList<Contact>();
                  _collectionContacts.put(_tmpKey, _tmpContactsCollection);
                }
              }
              if (!_cursor.isNull(_cursorIndexOfFileID)) {
                final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfFileID);
                ArrayList<File> _tmpFilesCollection = _collectionFiles.get(_tmpKey_1);
                if (_tmpFilesCollection == null) {
                  _tmpFilesCollection = new ArrayList<File>();
                  _collectionFiles.put(_tmpKey_1, _tmpFilesCollection);
                }
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipCONTACTSAscomKomandorKomandorDataDatabaseContactsContact(_collectionContacts);
            __fetchRelationshipFILESAscomKomandorKomandorDataDatabaseFilesFile(_collectionFiles);
            final MessageWithFileWithContact _result;
            if(_cursor.moveToFirst()) {
              final Message _tmpMessage;
              if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfMessageID) && _cursor.isNull(_cursorIndexOfChatID) && _cursor.isNull(_cursorIndexOfPid) && _cursor.isNull(_cursorIndexOfCid) && _cursor.isNull(_cursorIndexOfUserName) && _cursor.isNull(_cursorIndexOfData) && _cursor.isNull(_cursorIndexOfSign) && _cursor.isNull(_cursorIndexOfType) && _cursor.isNull(_cursorIndexOfFileID) && _cursor.isNull(_cursorIndexOfDate))) {
                _tmpMessage = new Message();
                final long _tmpId;
                _tmpId = _cursor.getLong(_cursorIndexOfId);
                _tmpMessage.setId(_tmpId);
                final int _tmpMessageID;
                _tmpMessageID = _cursor.getInt(_cursorIndexOfMessageID);
                _tmpMessage.setMessageID(_tmpMessageID);
                final String _tmpChatID;
                _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
                _tmpMessage.setChatID(_tmpChatID);
                final int _tmpPid;
                _tmpPid = _cursor.getInt(_cursorIndexOfPid);
                _tmpMessage.setPid(_tmpPid);
                final int _tmpCid;
                _tmpCid = _cursor.getInt(_cursorIndexOfCid);
                _tmpMessage.setCid(_tmpCid);
                final String _tmpUserName;
                _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
                _tmpMessage.setUserName(_tmpUserName);
                final String _tmpData;
                _tmpData = _cursor.getString(_cursorIndexOfData);
                _tmpMessage.setData(_tmpData);
                final String _tmpSign;
                _tmpSign = _cursor.getString(_cursorIndexOfSign);
                _tmpMessage.setSign(_tmpSign);
                final String _tmpType;
                _tmpType = _cursor.getString(_cursorIndexOfType);
                _tmpMessage.setType(_tmpType);
                final long _tmpFileID;
                _tmpFileID = _cursor.getLong(_cursorIndexOfFileID);
                _tmpMessage.setFileID(_tmpFileID);
                final long _tmpDate;
                _tmpDate = _cursor.getLong(_cursorIndexOfDate);
                _tmpMessage.setDate(_tmpDate);
              }  else  {
                _tmpMessage = null;
              }
              ArrayList<Contact> _tmpContactsCollection_1 = null;
              if (!_cursor.isNull(_cursorIndexOfPid)) {
                final long _tmpKey_2 = _cursor.getLong(_cursorIndexOfPid);
                _tmpContactsCollection_1 = _collectionContacts.get(_tmpKey_2);
              }
              if (_tmpContactsCollection_1 == null) {
                _tmpContactsCollection_1 = new ArrayList<Contact>();
              }
              ArrayList<File> _tmpFilesCollection_1 = null;
              if (!_cursor.isNull(_cursorIndexOfFileID)) {
                final long _tmpKey_3 = _cursor.getLong(_cursorIndexOfFileID);
                _tmpFilesCollection_1 = _collectionFiles.get(_tmpKey_3);
              }
              if (_tmpFilesCollection_1 == null) {
                _tmpFilesCollection_1 = new ArrayList<File>();
              }
              _result = new MessageWithFileWithContact();
              _result.message = _tmpMessage;
              _result.contacts = _tmpContactsCollection_1;
              _result.files = _tmpFilesCollection_1;
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public int getLastMessageID(final String chatID) {
    final String _sql = "select id from MESSAGES where chat_id like ? order by date desc limit 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (chatID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, chatID);
    }
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Integer> getAllKeys(final String chatID) {
    final String _sql = "select id from MESSAGES where chat_id like ? order by date desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (chatID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, chatID);
    }
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final List<Integer> _result = new ArrayList<Integer>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Integer _item;
        if (_cursor.isNull(0)) {
          _item = null;
        } else {
          _item = _cursor.getInt(0);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message> getMessagesByIDs(final List<Integer> ids) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("select * from MESSAGES where id in (");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(") order by date desc");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (Integer _item : ids) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindLong(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfMessageID = CursorUtil.getColumnIndexOrThrow(_cursor, "message_id");
      final int _cursorIndexOfChatID = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_id");
      final int _cursorIndexOfPid = CursorUtil.getColumnIndexOrThrow(_cursor, "pid");
      final int _cursorIndexOfCid = CursorUtil.getColumnIndexOrThrow(_cursor, "cid");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user");
      final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
      final int _cursorIndexOfSign = CursorUtil.getColumnIndexOrThrow(_cursor, "sign");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfFileID = CursorUtil.getColumnIndexOrThrow(_cursor, "file_id");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message _item_1;
        _item_1 = new Message();
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item_1.setId(_tmpId);
        final int _tmpMessageID;
        _tmpMessageID = _cursor.getInt(_cursorIndexOfMessageID);
        _item_1.setMessageID(_tmpMessageID);
        final String _tmpChatID;
        _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
        _item_1.setChatID(_tmpChatID);
        final int _tmpPid;
        _tmpPid = _cursor.getInt(_cursorIndexOfPid);
        _item_1.setPid(_tmpPid);
        final int _tmpCid;
        _tmpCid = _cursor.getInt(_cursorIndexOfCid);
        _item_1.setCid(_tmpCid);
        final String _tmpUserName;
        _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        _item_1.setUserName(_tmpUserName);
        final String _tmpData;
        _tmpData = _cursor.getString(_cursorIndexOfData);
        _item_1.setData(_tmpData);
        final String _tmpSign;
        _tmpSign = _cursor.getString(_cursorIndexOfSign);
        _item_1.setSign(_tmpSign);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _item_1.setType(_tmpType);
        final long _tmpFileID;
        _tmpFileID = _cursor.getLong(_cursorIndexOfFileID);
        _item_1.setFileID(_tmpFileID);
        final long _tmpDate;
        _tmpDate = _cursor.getLong(_cursorIndexOfDate);
        _item_1.setDate(_tmpDate);
        _result.add(_item_1);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  private void __fetchRelationshipCONTACTSAscomKomandorKomandorDataDatabaseContactsContact(
      final LongSparseArray<ArrayList<Contact>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<ArrayList<Contact>> _tmpInnerMap = new LongSparseArray<ArrayList<Contact>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _mapIndex = 0;
      int _tmpIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipCONTACTSAscomKomandorKomandorDataDatabaseContactsContact(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<ArrayList<Contact>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipCONTACTSAscomKomandorKomandorDataDatabaseContactsContact(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`type`,`pid`,`local_id`,`uid`,`contact_name`,`phone`,`unmasked_phone`,`full_name`,`surname`,`name`,`patronymic`,`company`,`title`,`photo`,`chat_id`,`is_updated` FROM `CONTACTS` WHERE `pid` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "pid");
      if (_itemKeyIndex == -1) {
        return;
      }
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
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final long _tmpKey = _cursor.getLong(_itemKeyIndex);
          ArrayList<Contact> _tmpCollection = _map.get(_tmpKey);
          if (_tmpCollection != null) {
            final Contact _item_1;
            _item_1 = new Contact();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item_1.setId(_tmpId);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            _item_1.setType(_tmpType);
            final int _tmpPid;
            _tmpPid = _cursor.getInt(_cursorIndexOfPid);
            _item_1.setPid(_tmpPid);
            final int _tmpLocalID;
            _tmpLocalID = _cursor.getInt(_cursorIndexOfLocalID);
            _item_1.setLocalID(_tmpLocalID);
            final int _tmpUid;
            _tmpUid = _cursor.getInt(_cursorIndexOfUid);
            _item_1.setUid(_tmpUid);
            final String _tmpContactName;
            _tmpContactName = _cursor.getString(_cursorIndexOfContactName);
            _item_1.setContactName(_tmpContactName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            _item_1.setPhone(_tmpPhone);
            final String _tmpUnmaskedPhone;
            _tmpUnmaskedPhone = _cursor.getString(_cursorIndexOfUnmaskedPhone);
            _item_1.setUnmaskedPhone(_tmpUnmaskedPhone);
            final String _tmpFullName;
            _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            _item_1.setFullName(_tmpFullName);
            final String _tmpSurname;
            _tmpSurname = _cursor.getString(_cursorIndexOfSurname);
            _item_1.setSurname(_tmpSurname);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item_1.setName(_tmpName);
            final String _tmpPatronymic;
            _tmpPatronymic = _cursor.getString(_cursorIndexOfPatronymic);
            _item_1.setPatronymic(_tmpPatronymic);
            final String _tmpCompany;
            _tmpCompany = _cursor.getString(_cursorIndexOfCompany);
            _item_1.setCompany(_tmpCompany);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item_1.setTitle(_tmpTitle);
            final String _tmpPhoto;
            _tmpPhoto = _cursor.getString(_cursorIndexOfPhoto);
            _item_1.setPhoto(_tmpPhoto);
            final String _tmpChatID;
            _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
            _item_1.setChatID(_tmpChatID);
            final boolean _tmpIsUpdated;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsUpdated);
            _tmpIsUpdated = _tmp != 0;
            _item_1.setUpdated(_tmpIsUpdated);
            _tmpCollection.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipFILESAscomKomandorKomandorDataDatabaseFilesFile(
      final LongSparseArray<ArrayList<File>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<ArrayList<File>> _tmpInnerMap = new LongSparseArray<ArrayList<File>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _mapIndex = 0;
      int _tmpIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipFILESAscomKomandorKomandorDataDatabaseFilesFile(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<ArrayList<File>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipFILESAscomKomandorKomandorDataDatabaseFilesFile(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`type`,`name`,`name_sign`,`file_id`,`cms`,`file_path` FROM `FILES` WHERE `id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfNameSign = CursorUtil.getColumnIndexOrThrow(_cursor, "name_sign");
      final int _cursorIndexOfFileID = CursorUtil.getColumnIndexOrThrow(_cursor, "file_id");
      final int _cursorIndexOfCms = CursorUtil.getColumnIndexOrThrow(_cursor, "cms");
      final int _cursorIndexOfFilePath = CursorUtil.getColumnIndexOrThrow(_cursor, "file_path");
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final long _tmpKey = _cursor.getLong(_itemKeyIndex);
          ArrayList<File> _tmpCollection = _map.get(_tmpKey);
          if (_tmpCollection != null) {
            final File _item_1;
            _item_1 = new File();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item_1.setId(_tmpId);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            _item_1.setType(_tmpType);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item_1.setName(_tmpName);
            final String _tmpNameSign;
            _tmpNameSign = _cursor.getString(_cursorIndexOfNameSign);
            _item_1.setNameSign(_tmpNameSign);
            final String _tmpFileID;
            _tmpFileID = _cursor.getString(_cursorIndexOfFileID);
            _item_1.setFileID(_tmpFileID);
            final String _tmpCms;
            _tmpCms = _cursor.getString(_cursorIndexOfCms);
            _item_1.setCms(_tmpCms);
            final String _tmpFilePath;
            _tmpFilePath = _cursor.getString(_cursorIndexOfFilePath);
            _item_1.setFilePath(_tmpFilePath);
            _tmpCollection.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
