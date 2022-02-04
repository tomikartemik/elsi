package com.komandor.komandor.data.database.chats;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.komandor.komandor.data.database.messages.Message;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public final class ChatDao_Impl extends ChatDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfChat;

  private final SharedSQLiteStatement __preparedStmtOfSetUnreadMessages;

  private final SharedSQLiteStatement __preparedStmtOfSetAllChatsOld;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldChats;

  private final SharedSQLiteStatement __preparedStmtOfSetLastMessageID;

  public ChatDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfChat = new EntityInsertionAdapter<Chat>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `CHATS`(`chat_id`,`type`,`name`,`last_message_id`,`key_id`,`key_import`,`key_base64`,`cert_base64`,`unread_messages`,`is_updated`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Chat value) {
        if (value.getChatID() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getChatID());
        }
        stmt.bindLong(2, value.getType());
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        stmt.bindLong(4, value.lastMessageID);
        stmt.bindLong(5, value.getKeyID());
        final int _tmp;
        _tmp = value.isKeyImport() ? 1 : 0;
        stmt.bindLong(6, _tmp);
        if (value.getKeyBase64() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getKeyBase64());
        }
        if (value.getCertBase64() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getCertBase64());
        }
        stmt.bindLong(9, value.getUnreadMessages());
        final int _tmp_1;
        _tmp_1 = value.isUpdated() ? 1 : 0;
        stmt.bindLong(10, _tmp_1);
      }
    };
    this.__preparedStmtOfSetUnreadMessages = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update CHATS set unread_messages = ? where chat_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetAllChatsOld = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update CHATS set is_updated = 0";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOldChats = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from CHATS where is_updated = 0";
        return _query;
      }
    };
    this.__preparedStmtOfSetLastMessageID = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update CHATS set last_message_id = ? where chat_id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Chat chat) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfChat.insert(chat);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertMany(final List<Chat> chats) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfChat.insert(chats);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertChats(final List<Chat> chats) {
    __db.beginTransaction();
    try {
      super.insertChats(chats);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void increaseUnreadMessages(final String chatID) {
    __db.beginTransaction();
    try {
      super.increaseUnreadMessages(chatID);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void setUnreadMessages(final String chatID, final int unreadMessages) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetUnreadMessages.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, unreadMessages);
      _argIndex = 2;
      if (chatID == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, chatID);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetUnreadMessages.release(_stmt);
    }
  }

  @Override
  public void setAllChatsOld() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetAllChatsOld.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetAllChatsOld.release(_stmt);
    }
  }

  @Override
  public void deleteOldChats() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldChats.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteOldChats.release(_stmt);
    }
  }

  @Override
  public void setLastMessageID(final String chatID, final long id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetLastMessageID.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, id);
      _argIndex = 2;
      if (chatID == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, chatID);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetLastMessageID.release(_stmt);
    }
  }

  @Override
  public Flowable<List<Chat>> getChats() {
    final String _sql = "select * from CHATS";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"CHATS"}, new Callable<List<Chat>>() {
      @Override
      public List<Chat> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfChatID = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfLastMessageID = CursorUtil.getColumnIndexOrThrow(_cursor, "last_message_id");
          final int _cursorIndexOfKeyID = CursorUtil.getColumnIndexOrThrow(_cursor, "key_id");
          final int _cursorIndexOfKeyImport = CursorUtil.getColumnIndexOrThrow(_cursor, "key_import");
          final int _cursorIndexOfKeyBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "key_base64");
          final int _cursorIndexOfCertBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "cert_base64");
          final int _cursorIndexOfUnreadMessages = CursorUtil.getColumnIndexOrThrow(_cursor, "unread_messages");
          final int _cursorIndexOfIsUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "is_updated");
          final List<Chat> _result = new ArrayList<Chat>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Chat _item;
            _item = new Chat();
            final String _tmpChatID;
            _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
            _item.setChatID(_tmpChatID);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            _item.setType(_tmpType);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
            _item.lastMessageID = _cursor.getInt(_cursorIndexOfLastMessageID);
            final int _tmpKeyID;
            _tmpKeyID = _cursor.getInt(_cursorIndexOfKeyID);
            _item.setKeyID(_tmpKeyID);
            final boolean _tmpKeyImport;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfKeyImport);
            _tmpKeyImport = _tmp != 0;
            _item.setKeyImport(_tmpKeyImport);
            final String _tmpKeyBase64;
            _tmpKeyBase64 = _cursor.getString(_cursorIndexOfKeyBase64);
            _item.setKeyBase64(_tmpKeyBase64);
            final String _tmpCertBase64;
            _tmpCertBase64 = _cursor.getString(_cursorIndexOfCertBase64);
            _item.setCertBase64(_tmpCertBase64);
            final int _tmpUnreadMessages;
            _tmpUnreadMessages = _cursor.getInt(_cursorIndexOfUnreadMessages);
            _item.setUnreadMessages(_tmpUnreadMessages);
            final boolean _tmpIsUpdated;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsUpdated);
            _tmpIsUpdated = _tmp_1 != 0;
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
  public Flowable<List<ChatWithLastMessage>> getChatsWithLastMessage() {
    final String _sql = "select * from CHATS order by name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, new String[]{"MESSAGES","CHATS"}, new Callable<List<ChatWithLastMessage>>() {
      @Override
      public List<ChatWithLastMessage> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true);
          try {
            final int _cursorIndexOfChatID = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_id");
            final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfLastMessageID = CursorUtil.getColumnIndexOrThrow(_cursor, "last_message_id");
            final int _cursorIndexOfKeyID = CursorUtil.getColumnIndexOrThrow(_cursor, "key_id");
            final int _cursorIndexOfKeyImport = CursorUtil.getColumnIndexOrThrow(_cursor, "key_import");
            final int _cursorIndexOfKeyBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "key_base64");
            final int _cursorIndexOfCertBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "cert_base64");
            final int _cursorIndexOfUnreadMessages = CursorUtil.getColumnIndexOrThrow(_cursor, "unread_messages");
            final int _cursorIndexOfIsUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "is_updated");
            final LongSparseArray<ArrayList<Message>> _collectionMessages = new LongSparseArray<ArrayList<Message>>();
            while (_cursor.moveToNext()) {
              if (!_cursor.isNull(_cursorIndexOfLastMessageID)) {
                final long _tmpKey = _cursor.getLong(_cursorIndexOfLastMessageID);
                ArrayList<Message> _tmpMessagesCollection = _collectionMessages.get(_tmpKey);
                if (_tmpMessagesCollection == null) {
                  _tmpMessagesCollection = new ArrayList<Message>();
                  _collectionMessages.put(_tmpKey, _tmpMessagesCollection);
                }
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipMESSAGESAscomKomandorKomandorDataDatabaseMessagesMessage(_collectionMessages);
            final List<ChatWithLastMessage> _result = new ArrayList<ChatWithLastMessage>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final ChatWithLastMessage _item;
              final Chat _tmpChat;
              if (! (_cursor.isNull(_cursorIndexOfChatID) && _cursor.isNull(_cursorIndexOfType) && _cursor.isNull(_cursorIndexOfName) && _cursor.isNull(_cursorIndexOfLastMessageID) && _cursor.isNull(_cursorIndexOfKeyID) && _cursor.isNull(_cursorIndexOfKeyImport) && _cursor.isNull(_cursorIndexOfKeyBase64) && _cursor.isNull(_cursorIndexOfCertBase64) && _cursor.isNull(_cursorIndexOfUnreadMessages) && _cursor.isNull(_cursorIndexOfIsUpdated))) {
                _tmpChat = new Chat();
                final String _tmpChatID;
                _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
                _tmpChat.setChatID(_tmpChatID);
                final int _tmpType;
                _tmpType = _cursor.getInt(_cursorIndexOfType);
                _tmpChat.setType(_tmpType);
                final String _tmpName;
                _tmpName = _cursor.getString(_cursorIndexOfName);
                _tmpChat.setName(_tmpName);
                _tmpChat.lastMessageID = _cursor.getInt(_cursorIndexOfLastMessageID);
                final int _tmpKeyID;
                _tmpKeyID = _cursor.getInt(_cursorIndexOfKeyID);
                _tmpChat.setKeyID(_tmpKeyID);
                final boolean _tmpKeyImport;
                final int _tmp;
                _tmp = _cursor.getInt(_cursorIndexOfKeyImport);
                _tmpKeyImport = _tmp != 0;
                _tmpChat.setKeyImport(_tmpKeyImport);
                final String _tmpKeyBase64;
                _tmpKeyBase64 = _cursor.getString(_cursorIndexOfKeyBase64);
                _tmpChat.setKeyBase64(_tmpKeyBase64);
                final String _tmpCertBase64;
                _tmpCertBase64 = _cursor.getString(_cursorIndexOfCertBase64);
                _tmpChat.setCertBase64(_tmpCertBase64);
                final int _tmpUnreadMessages;
                _tmpUnreadMessages = _cursor.getInt(_cursorIndexOfUnreadMessages);
                _tmpChat.setUnreadMessages(_tmpUnreadMessages);
                final boolean _tmpIsUpdated;
                final int _tmp_1;
                _tmp_1 = _cursor.getInt(_cursorIndexOfIsUpdated);
                _tmpIsUpdated = _tmp_1 != 0;
                _tmpChat.setUpdated(_tmpIsUpdated);
              }  else  {
                _tmpChat = null;
              }
              ArrayList<Message> _tmpMessagesCollection_1 = null;
              if (!_cursor.isNull(_cursorIndexOfLastMessageID)) {
                final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfLastMessageID);
                _tmpMessagesCollection_1 = _collectionMessages.get(_tmpKey_1);
              }
              if (_tmpMessagesCollection_1 == null) {
                _tmpMessagesCollection_1 = new ArrayList<Message>();
              }
              _item = new ChatWithLastMessage();
              _item.chat = _tmpChat;
              _item.messages = _tmpMessagesCollection_1;
              _result.add(_item);
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
  public Flowable<ChatWithMessages> getChatWithMessages(final String chatID) {
    final String _sql = "select * from CHATS where chat_id like ? order by name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (chatID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, chatID);
    }
    return RxRoom.createFlowable(__db, new String[]{"MESSAGES","CHATS"}, new Callable<ChatWithMessages>() {
      @Override
      public ChatWithMessages call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true);
          try {
            final int _cursorIndexOfChatID = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_id");
            final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfLastMessageID = CursorUtil.getColumnIndexOrThrow(_cursor, "last_message_id");
            final int _cursorIndexOfKeyID = CursorUtil.getColumnIndexOrThrow(_cursor, "key_id");
            final int _cursorIndexOfKeyImport = CursorUtil.getColumnIndexOrThrow(_cursor, "key_import");
            final int _cursorIndexOfKeyBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "key_base64");
            final int _cursorIndexOfCertBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "cert_base64");
            final int _cursorIndexOfUnreadMessages = CursorUtil.getColumnIndexOrThrow(_cursor, "unread_messages");
            final int _cursorIndexOfIsUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "is_updated");
            final ArrayMap<String, ArrayList<Message>> _collectionMessages = new ArrayMap<String, ArrayList<Message>>();
            while (_cursor.moveToNext()) {
              if (!_cursor.isNull(_cursorIndexOfChatID)) {
                final String _tmpKey = _cursor.getString(_cursorIndexOfChatID);
                ArrayList<Message> _tmpMessagesCollection = _collectionMessages.get(_tmpKey);
                if (_tmpMessagesCollection == null) {
                  _tmpMessagesCollection = new ArrayList<Message>();
                  _collectionMessages.put(_tmpKey, _tmpMessagesCollection);
                }
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipMESSAGESAscomKomandorKomandorDataDatabaseMessagesMessage_1(_collectionMessages);
            final ChatWithMessages _result;
            if(_cursor.moveToFirst()) {
              final Chat _tmpChat;
              if (! (_cursor.isNull(_cursorIndexOfChatID) && _cursor.isNull(_cursorIndexOfType) && _cursor.isNull(_cursorIndexOfName) && _cursor.isNull(_cursorIndexOfLastMessageID) && _cursor.isNull(_cursorIndexOfKeyID) && _cursor.isNull(_cursorIndexOfKeyImport) && _cursor.isNull(_cursorIndexOfKeyBase64) && _cursor.isNull(_cursorIndexOfCertBase64) && _cursor.isNull(_cursorIndexOfUnreadMessages) && _cursor.isNull(_cursorIndexOfIsUpdated))) {
                _tmpChat = new Chat();
                final String _tmpChatID;
                _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
                _tmpChat.setChatID(_tmpChatID);
                final int _tmpType;
                _tmpType = _cursor.getInt(_cursorIndexOfType);
                _tmpChat.setType(_tmpType);
                final String _tmpName;
                _tmpName = _cursor.getString(_cursorIndexOfName);
                _tmpChat.setName(_tmpName);
                _tmpChat.lastMessageID = _cursor.getInt(_cursorIndexOfLastMessageID);
                final int _tmpKeyID;
                _tmpKeyID = _cursor.getInt(_cursorIndexOfKeyID);
                _tmpChat.setKeyID(_tmpKeyID);
                final boolean _tmpKeyImport;
                final int _tmp;
                _tmp = _cursor.getInt(_cursorIndexOfKeyImport);
                _tmpKeyImport = _tmp != 0;
                _tmpChat.setKeyImport(_tmpKeyImport);
                final String _tmpKeyBase64;
                _tmpKeyBase64 = _cursor.getString(_cursorIndexOfKeyBase64);
                _tmpChat.setKeyBase64(_tmpKeyBase64);
                final String _tmpCertBase64;
                _tmpCertBase64 = _cursor.getString(_cursorIndexOfCertBase64);
                _tmpChat.setCertBase64(_tmpCertBase64);
                final int _tmpUnreadMessages;
                _tmpUnreadMessages = _cursor.getInt(_cursorIndexOfUnreadMessages);
                _tmpChat.setUnreadMessages(_tmpUnreadMessages);
                final boolean _tmpIsUpdated;
                final int _tmp_1;
                _tmp_1 = _cursor.getInt(_cursorIndexOfIsUpdated);
                _tmpIsUpdated = _tmp_1 != 0;
                _tmpChat.setUpdated(_tmpIsUpdated);
              }  else  {
                _tmpChat = null;
              }
              ArrayList<Message> _tmpMessagesCollection_1 = null;
              if (!_cursor.isNull(_cursorIndexOfChatID)) {
                final String _tmpKey_1 = _cursor.getString(_cursorIndexOfChatID);
                _tmpMessagesCollection_1 = _collectionMessages.get(_tmpKey_1);
              }
              if (_tmpMessagesCollection_1 == null) {
                _tmpMessagesCollection_1 = new ArrayList<Message>();
              }
              _result = new ChatWithMessages();
              _result.chat = _tmpChat;
              _result.messages = _tmpMessagesCollection_1;
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
  public Flowable<Chat> getChatByID(final String chatID) {
    final String _sql = "select * from CHATS where chat_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (chatID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, chatID);
    }
    return RxRoom.createFlowable(__db, new String[]{"CHATS"}, new Callable<Chat>() {
      @Override
      public Chat call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfChatID = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfLastMessageID = CursorUtil.getColumnIndexOrThrow(_cursor, "last_message_id");
          final int _cursorIndexOfKeyID = CursorUtil.getColumnIndexOrThrow(_cursor, "key_id");
          final int _cursorIndexOfKeyImport = CursorUtil.getColumnIndexOrThrow(_cursor, "key_import");
          final int _cursorIndexOfKeyBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "key_base64");
          final int _cursorIndexOfCertBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "cert_base64");
          final int _cursorIndexOfUnreadMessages = CursorUtil.getColumnIndexOrThrow(_cursor, "unread_messages");
          final int _cursorIndexOfIsUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "is_updated");
          final Chat _result;
          if(_cursor.moveToFirst()) {
            _result = new Chat();
            final String _tmpChatID;
            _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
            _result.setChatID(_tmpChatID);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            _result.setType(_tmpType);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _result.setName(_tmpName);
            _result.lastMessageID = _cursor.getInt(_cursorIndexOfLastMessageID);
            final int _tmpKeyID;
            _tmpKeyID = _cursor.getInt(_cursorIndexOfKeyID);
            _result.setKeyID(_tmpKeyID);
            final boolean _tmpKeyImport;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfKeyImport);
            _tmpKeyImport = _tmp != 0;
            _result.setKeyImport(_tmpKeyImport);
            final String _tmpKeyBase64;
            _tmpKeyBase64 = _cursor.getString(_cursorIndexOfKeyBase64);
            _result.setKeyBase64(_tmpKeyBase64);
            final String _tmpCertBase64;
            _tmpCertBase64 = _cursor.getString(_cursorIndexOfCertBase64);
            _result.setCertBase64(_tmpCertBase64);
            final int _tmpUnreadMessages;
            _tmpUnreadMessages = _cursor.getInt(_cursorIndexOfUnreadMessages);
            _result.setUnreadMessages(_tmpUnreadMessages);
            final boolean _tmpIsUpdated;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsUpdated);
            _tmpIsUpdated = _tmp_1 != 0;
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
  public Chat getChatByIDSync(final String chatID) {
    final String _sql = "select * from CHATS where chat_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (chatID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, chatID);
    }
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfChatID = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_id");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfLastMessageID = CursorUtil.getColumnIndexOrThrow(_cursor, "last_message_id");
      final int _cursorIndexOfKeyID = CursorUtil.getColumnIndexOrThrow(_cursor, "key_id");
      final int _cursorIndexOfKeyImport = CursorUtil.getColumnIndexOrThrow(_cursor, "key_import");
      final int _cursorIndexOfKeyBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "key_base64");
      final int _cursorIndexOfCertBase64 = CursorUtil.getColumnIndexOrThrow(_cursor, "cert_base64");
      final int _cursorIndexOfUnreadMessages = CursorUtil.getColumnIndexOrThrow(_cursor, "unread_messages");
      final int _cursorIndexOfIsUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "is_updated");
      final Chat _result;
      if(_cursor.moveToFirst()) {
        _result = new Chat();
        final String _tmpChatID;
        _tmpChatID = _cursor.getString(_cursorIndexOfChatID);
        _result.setChatID(_tmpChatID);
        final int _tmpType;
        _tmpType = _cursor.getInt(_cursorIndexOfType);
        _result.setType(_tmpType);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
        _result.lastMessageID = _cursor.getInt(_cursorIndexOfLastMessageID);
        final int _tmpKeyID;
        _tmpKeyID = _cursor.getInt(_cursorIndexOfKeyID);
        _result.setKeyID(_tmpKeyID);
        final boolean _tmpKeyImport;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfKeyImport);
        _tmpKeyImport = _tmp != 0;
        _result.setKeyImport(_tmpKeyImport);
        final String _tmpKeyBase64;
        _tmpKeyBase64 = _cursor.getString(_cursorIndexOfKeyBase64);
        _result.setKeyBase64(_tmpKeyBase64);
        final String _tmpCertBase64;
        _tmpCertBase64 = _cursor.getString(_cursorIndexOfCertBase64);
        _result.setCertBase64(_tmpCertBase64);
        final int _tmpUnreadMessages;
        _tmpUnreadMessages = _cursor.getInt(_cursorIndexOfUnreadMessages);
        _result.setUnreadMessages(_tmpUnreadMessages);
        final boolean _tmpIsUpdated;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsUpdated);
        _tmpIsUpdated = _tmp_1 != 0;
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

  private void __fetchRelationshipMESSAGESAscomKomandorKomandorDataDatabaseMessagesMessage(
      final LongSparseArray<ArrayList<Message>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<ArrayList<Message>> _tmpInnerMap = new LongSparseArray<ArrayList<Message>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _mapIndex = 0;
      int _tmpIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipMESSAGESAscomKomandorKomandorDataDatabaseMessagesMessage(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<ArrayList<Message>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipMESSAGESAscomKomandorKomandorDataDatabaseMessagesMessage(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`message_id`,`chat_id`,`pid`,`cid`,`user`,`data`,`sign`,`type`,`file_id`,`date` FROM `MESSAGES` WHERE `id` IN (");
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
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final long _tmpKey = _cursor.getLong(_itemKeyIndex);
          ArrayList<Message> _tmpCollection = _map.get(_tmpKey);
          if (_tmpCollection != null) {
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
            _tmpCollection.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }

  private void __fetchRelationshipMESSAGESAscomKomandorKomandorDataDatabaseMessagesMessage_1(
      final ArrayMap<String, ArrayList<Message>> _map) {
    final Set<String> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<String, ArrayList<Message>> _tmpInnerMap = new ArrayMap<String, ArrayList<Message>>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _mapIndex = 0;
      int _tmpIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipMESSAGESAscomKomandorKomandorDataDatabaseMessagesMessage_1(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<String, ArrayList<Message>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipMESSAGESAscomKomandorKomandorDataDatabaseMessagesMessage_1(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`message_id`,`chat_id`,`pid`,`cid`,`user`,`data`,`sign`,`type`,`file_id`,`date` FROM `MESSAGES` WHERE `chat_id` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "chat_id");
      if (_itemKeyIndex == -1) {
        return;
      }
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
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final String _tmpKey = _cursor.getString(_itemKeyIndex);
          ArrayList<Message> _tmpCollection = _map.get(_tmpKey);
          if (_tmpCollection != null) {
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
            _tmpCollection.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
