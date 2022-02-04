package com.komandor.komandor.data.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.komandor.komandor.data.database.certificates.CertificateDao;
import com.komandor.komandor.data.database.certificates.CertificateDao_Impl;
import com.komandor.komandor.data.database.chats.ChatDao;
import com.komandor.komandor.data.database.chats.ChatDao_Impl;
import com.komandor.komandor.data.database.contacts.ContactDao;
import com.komandor.komandor.data.database.contacts.ContactDao_Impl;
import com.komandor.komandor.data.database.contacts.ContactsCertificatesDao;
import com.komandor.komandor.data.database.contacts.ContactsCertificatesDao_Impl;
import com.komandor.komandor.data.database.files.FileDao;
import com.komandor.komandor.data.database.files.FileDao_Impl;
import com.komandor.komandor.data.database.messages.MessageDao;
import com.komandor.komandor.data.database.messages.MessageDao_Impl;
import com.komandor.komandor.data.database.users.UserDao;
import com.komandor.komandor.data.database.users.UserDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class KomandorDatabase_Impl extends KomandorDatabase {
  private volatile UserDao _userDao;

  private volatile CertificateDao _certificateDao;

  private volatile ContactDao _contactDao;

  private volatile ContactsCertificatesDao _contactsCertificatesDao;

  private volatile ChatDao _chatDao;

  private volatile MessageDao _messageDao;

  private volatile FileDao _fileDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ACCOUNTS` (`user_id` INTEGER NOT NULL, `cert_id` INTEGER NOT NULL, `type` INTEGER NOT NULL, `surname` TEXT, `name` TEXT, `patronymic` TEXT, `company` TEXT, `title` TEXT, `INN` TEXT, `OGRN` TEXT, `SNILS` TEXT, `phone` TEXT, `photo` TEXT, `email` TEXT, PRIMARY KEY(`user_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CERTIFICATES` (`cert_id` INTEGER NOT NULL, `contact_id` INTEGER NOT NULL, `is_local` INTEGER NOT NULL, `is_active` INTEGER NOT NULL, `base64` TEXT, PRIMARY KEY(`cert_id`))");
        _db.execSQL("CREATE UNIQUE INDEX `index_CERTIFICATES_cert_id` ON `CERTIFICATES` (`cert_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CONTACTS` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `type` INTEGER NOT NULL, `pid` INTEGER NOT NULL, `local_id` INTEGER NOT NULL, `uid` INTEGER NOT NULL, `contact_name` TEXT, `phone` TEXT, `unmasked_phone` TEXT, `full_name` TEXT, `surname` TEXT, `name` TEXT, `patronymic` TEXT, `company` TEXT, `title` TEXT, `photo` TEXT, `chat_id` TEXT, `is_updated` INTEGER NOT NULL)");
        _db.execSQL("CREATE UNIQUE INDEX `index_CONTACTS_pid_local_id` ON `CONTACTS` (`pid`, `local_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `CHATS` (`chat_id` TEXT NOT NULL, `type` INTEGER NOT NULL, `name` TEXT, `last_message_id` INTEGER NOT NULL, `key_id` INTEGER NOT NULL, `key_import` INTEGER NOT NULL, `key_base64` TEXT, `cert_base64` TEXT, `unread_messages` INTEGER NOT NULL, `is_updated` INTEGER NOT NULL, PRIMARY KEY(`chat_id`))");
        _db.execSQL("CREATE UNIQUE INDEX `index_CHATS_chat_id` ON `CHATS` (`chat_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MESSAGES` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `message_id` INTEGER NOT NULL, `chat_id` TEXT, `pid` INTEGER NOT NULL, `cid` INTEGER NOT NULL, `user` TEXT, `data` TEXT, `sign` TEXT, `type` TEXT, `file_id` INTEGER NOT NULL, `date` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `FILES` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `type` INTEGER NOT NULL, `name` TEXT, `name_sign` TEXT, `file_id` TEXT, `cms` TEXT, `file_path` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"bbe89afd849931c9b50fd94c2133bfea\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `ACCOUNTS`");
        _db.execSQL("DROP TABLE IF EXISTS `CERTIFICATES`");
        _db.execSQL("DROP TABLE IF EXISTS `CONTACTS`");
        _db.execSQL("DROP TABLE IF EXISTS `CHATS`");
        _db.execSQL("DROP TABLE IF EXISTS `MESSAGES`");
        _db.execSQL("DROP TABLE IF EXISTS `FILES`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsACCOUNTS = new HashMap<String, TableInfo.Column>(14);
        _columnsACCOUNTS.put("user_id", new TableInfo.Column("user_id", "INTEGER", true, 1));
        _columnsACCOUNTS.put("cert_id", new TableInfo.Column("cert_id", "INTEGER", true, 0));
        _columnsACCOUNTS.put("type", new TableInfo.Column("type", "INTEGER", true, 0));
        _columnsACCOUNTS.put("surname", new TableInfo.Column("surname", "TEXT", false, 0));
        _columnsACCOUNTS.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsACCOUNTS.put("patronymic", new TableInfo.Column("patronymic", "TEXT", false, 0));
        _columnsACCOUNTS.put("company", new TableInfo.Column("company", "TEXT", false, 0));
        _columnsACCOUNTS.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsACCOUNTS.put("INN", new TableInfo.Column("INN", "TEXT", false, 0));
        _columnsACCOUNTS.put("OGRN", new TableInfo.Column("OGRN", "TEXT", false, 0));
        _columnsACCOUNTS.put("SNILS", new TableInfo.Column("SNILS", "TEXT", false, 0));
        _columnsACCOUNTS.put("phone", new TableInfo.Column("phone", "TEXT", false, 0));
        _columnsACCOUNTS.put("photo", new TableInfo.Column("photo", "TEXT", false, 0));
        _columnsACCOUNTS.put("email", new TableInfo.Column("email", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysACCOUNTS = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesACCOUNTS = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoACCOUNTS = new TableInfo("ACCOUNTS", _columnsACCOUNTS, _foreignKeysACCOUNTS, _indicesACCOUNTS);
        final TableInfo _existingACCOUNTS = TableInfo.read(_db, "ACCOUNTS");
        if (! _infoACCOUNTS.equals(_existingACCOUNTS)) {
          throw new IllegalStateException("Migration didn't properly handle ACCOUNTS(com.komandor.komandor.data.database.users.User).\n"
                  + " Expected:\n" + _infoACCOUNTS + "\n"
                  + " Found:\n" + _existingACCOUNTS);
        }
        final HashMap<String, TableInfo.Column> _columnsCERTIFICATES = new HashMap<String, TableInfo.Column>(5);
        _columnsCERTIFICATES.put("cert_id", new TableInfo.Column("cert_id", "INTEGER", true, 1));
        _columnsCERTIFICATES.put("contact_id", new TableInfo.Column("contact_id", "INTEGER", true, 0));
        _columnsCERTIFICATES.put("is_local", new TableInfo.Column("is_local", "INTEGER", true, 0));
        _columnsCERTIFICATES.put("is_active", new TableInfo.Column("is_active", "INTEGER", true, 0));
        _columnsCERTIFICATES.put("base64", new TableInfo.Column("base64", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCERTIFICATES = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCERTIFICATES = new HashSet<TableInfo.Index>(1);
        _indicesCERTIFICATES.add(new TableInfo.Index("index_CERTIFICATES_cert_id", true, Arrays.asList("cert_id")));
        final TableInfo _infoCERTIFICATES = new TableInfo("CERTIFICATES", _columnsCERTIFICATES, _foreignKeysCERTIFICATES, _indicesCERTIFICATES);
        final TableInfo _existingCERTIFICATES = TableInfo.read(_db, "CERTIFICATES");
        if (! _infoCERTIFICATES.equals(_existingCERTIFICATES)) {
          throw new IllegalStateException("Migration didn't properly handle CERTIFICATES(com.komandor.komandor.data.database.certificates.Certificate).\n"
                  + " Expected:\n" + _infoCERTIFICATES + "\n"
                  + " Found:\n" + _existingCERTIFICATES);
        }
        final HashMap<String, TableInfo.Column> _columnsCONTACTS = new HashMap<String, TableInfo.Column>(17);
        _columnsCONTACTS.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsCONTACTS.put("type", new TableInfo.Column("type", "INTEGER", true, 0));
        _columnsCONTACTS.put("pid", new TableInfo.Column("pid", "INTEGER", true, 0));
        _columnsCONTACTS.put("local_id", new TableInfo.Column("local_id", "INTEGER", true, 0));
        _columnsCONTACTS.put("uid", new TableInfo.Column("uid", "INTEGER", true, 0));
        _columnsCONTACTS.put("contact_name", new TableInfo.Column("contact_name", "TEXT", false, 0));
        _columnsCONTACTS.put("phone", new TableInfo.Column("phone", "TEXT", false, 0));
        _columnsCONTACTS.put("unmasked_phone", new TableInfo.Column("unmasked_phone", "TEXT", false, 0));
        _columnsCONTACTS.put("full_name", new TableInfo.Column("full_name", "TEXT", false, 0));
        _columnsCONTACTS.put("surname", new TableInfo.Column("surname", "TEXT", false, 0));
        _columnsCONTACTS.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsCONTACTS.put("patronymic", new TableInfo.Column("patronymic", "TEXT", false, 0));
        _columnsCONTACTS.put("company", new TableInfo.Column("company", "TEXT", false, 0));
        _columnsCONTACTS.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsCONTACTS.put("photo", new TableInfo.Column("photo", "TEXT", false, 0));
        _columnsCONTACTS.put("chat_id", new TableInfo.Column("chat_id", "TEXT", false, 0));
        _columnsCONTACTS.put("is_updated", new TableInfo.Column("is_updated", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCONTACTS = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCONTACTS = new HashSet<TableInfo.Index>(1);
        _indicesCONTACTS.add(new TableInfo.Index("index_CONTACTS_pid_local_id", true, Arrays.asList("pid","local_id")));
        final TableInfo _infoCONTACTS = new TableInfo("CONTACTS", _columnsCONTACTS, _foreignKeysCONTACTS, _indicesCONTACTS);
        final TableInfo _existingCONTACTS = TableInfo.read(_db, "CONTACTS");
        if (! _infoCONTACTS.equals(_existingCONTACTS)) {
          throw new IllegalStateException("Migration didn't properly handle CONTACTS(com.komandor.komandor.data.database.contacts.Contact).\n"
                  + " Expected:\n" + _infoCONTACTS + "\n"
                  + " Found:\n" + _existingCONTACTS);
        }
        final HashMap<String, TableInfo.Column> _columnsCHATS = new HashMap<String, TableInfo.Column>(10);
        _columnsCHATS.put("chat_id", new TableInfo.Column("chat_id", "TEXT", true, 1));
        _columnsCHATS.put("type", new TableInfo.Column("type", "INTEGER", true, 0));
        _columnsCHATS.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsCHATS.put("last_message_id", new TableInfo.Column("last_message_id", "INTEGER", true, 0));
        _columnsCHATS.put("key_id", new TableInfo.Column("key_id", "INTEGER", true, 0));
        _columnsCHATS.put("key_import", new TableInfo.Column("key_import", "INTEGER", true, 0));
        _columnsCHATS.put("key_base64", new TableInfo.Column("key_base64", "TEXT", false, 0));
        _columnsCHATS.put("cert_base64", new TableInfo.Column("cert_base64", "TEXT", false, 0));
        _columnsCHATS.put("unread_messages", new TableInfo.Column("unread_messages", "INTEGER", true, 0));
        _columnsCHATS.put("is_updated", new TableInfo.Column("is_updated", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCHATS = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCHATS = new HashSet<TableInfo.Index>(1);
        _indicesCHATS.add(new TableInfo.Index("index_CHATS_chat_id", true, Arrays.asList("chat_id")));
        final TableInfo _infoCHATS = new TableInfo("CHATS", _columnsCHATS, _foreignKeysCHATS, _indicesCHATS);
        final TableInfo _existingCHATS = TableInfo.read(_db, "CHATS");
        if (! _infoCHATS.equals(_existingCHATS)) {
          throw new IllegalStateException("Migration didn't properly handle CHATS(com.komandor.komandor.data.database.chats.Chat).\n"
                  + " Expected:\n" + _infoCHATS + "\n"
                  + " Found:\n" + _existingCHATS);
        }
        final HashMap<String, TableInfo.Column> _columnsMESSAGES = new HashMap<String, TableInfo.Column>(11);
        _columnsMESSAGES.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsMESSAGES.put("message_id", new TableInfo.Column("message_id", "INTEGER", true, 0));
        _columnsMESSAGES.put("chat_id", new TableInfo.Column("chat_id", "TEXT", false, 0));
        _columnsMESSAGES.put("pid", new TableInfo.Column("pid", "INTEGER", true, 0));
        _columnsMESSAGES.put("cid", new TableInfo.Column("cid", "INTEGER", true, 0));
        _columnsMESSAGES.put("user", new TableInfo.Column("user", "TEXT", false, 0));
        _columnsMESSAGES.put("data", new TableInfo.Column("data", "TEXT", false, 0));
        _columnsMESSAGES.put("sign", new TableInfo.Column("sign", "TEXT", false, 0));
        _columnsMESSAGES.put("type", new TableInfo.Column("type", "TEXT", false, 0));
        _columnsMESSAGES.put("file_id", new TableInfo.Column("file_id", "INTEGER", true, 0));
        _columnsMESSAGES.put("date", new TableInfo.Column("date", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMESSAGES = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMESSAGES = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMESSAGES = new TableInfo("MESSAGES", _columnsMESSAGES, _foreignKeysMESSAGES, _indicesMESSAGES);
        final TableInfo _existingMESSAGES = TableInfo.read(_db, "MESSAGES");
        if (! _infoMESSAGES.equals(_existingMESSAGES)) {
          throw new IllegalStateException("Migration didn't properly handle MESSAGES(com.komandor.komandor.data.database.messages.Message).\n"
                  + " Expected:\n" + _infoMESSAGES + "\n"
                  + " Found:\n" + _existingMESSAGES);
        }
        final HashMap<String, TableInfo.Column> _columnsFILES = new HashMap<String, TableInfo.Column>(7);
        _columnsFILES.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsFILES.put("type", new TableInfo.Column("type", "INTEGER", true, 0));
        _columnsFILES.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsFILES.put("name_sign", new TableInfo.Column("name_sign", "TEXT", false, 0));
        _columnsFILES.put("file_id", new TableInfo.Column("file_id", "TEXT", false, 0));
        _columnsFILES.put("cms", new TableInfo.Column("cms", "TEXT", false, 0));
        _columnsFILES.put("file_path", new TableInfo.Column("file_path", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFILES = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFILES = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFILES = new TableInfo("FILES", _columnsFILES, _foreignKeysFILES, _indicesFILES);
        final TableInfo _existingFILES = TableInfo.read(_db, "FILES");
        if (! _infoFILES.equals(_existingFILES)) {
          throw new IllegalStateException("Migration didn't properly handle FILES(com.komandor.komandor.data.database.files.File).\n"
                  + " Expected:\n" + _infoFILES + "\n"
                  + " Found:\n" + _existingFILES);
        }
      }
    }, "bbe89afd849931c9b50fd94c2133bfea", "5fd76d86eef09aa16d4c323c9abdbeb5");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "ACCOUNTS","CERTIFICATES","CONTACTS","CHATS","MESSAGES","FILES");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `ACCOUNTS`");
      _db.execSQL("DELETE FROM `CERTIFICATES`");
      _db.execSQL("DELETE FROM `CONTACTS`");
      _db.execSQL("DELETE FROM `CHATS`");
      _db.execSQL("DELETE FROM `MESSAGES`");
      _db.execSQL("DELETE FROM `FILES`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public CertificateDao certificateDao() {
    if (_certificateDao != null) {
      return _certificateDao;
    } else {
      synchronized(this) {
        if(_certificateDao == null) {
          _certificateDao = new CertificateDao_Impl(this);
        }
        return _certificateDao;
      }
    }
  }

  @Override
  public ContactDao contactDao() {
    if (_contactDao != null) {
      return _contactDao;
    } else {
      synchronized(this) {
        if(_contactDao == null) {
          _contactDao = new ContactDao_Impl(this);
        }
        return _contactDao;
      }
    }
  }

  @Override
  public ContactsCertificatesDao contactsCertificatesDao() {
    if (_contactsCertificatesDao != null) {
      return _contactsCertificatesDao;
    } else {
      synchronized(this) {
        if(_contactsCertificatesDao == null) {
          _contactsCertificatesDao = new ContactsCertificatesDao_Impl(this);
        }
        return _contactsCertificatesDao;
      }
    }
  }

  @Override
  public ChatDao chatDao() {
    if (_chatDao != null) {
      return _chatDao;
    } else {
      synchronized(this) {
        if(_chatDao == null) {
          _chatDao = new ChatDao_Impl(this);
        }
        return _chatDao;
      }
    }
  }

  @Override
  public MessageDao messageDao() {
    if (_messageDao != null) {
      return _messageDao;
    } else {
      synchronized(this) {
        if(_messageDao == null) {
          _messageDao = new MessageDao_Impl(this);
        }
        return _messageDao;
      }
    }
  }

  @Override
  public FileDao fileDao() {
    if (_fileDao != null) {
      return _fileDao;
    } else {
      synchronized(this) {
        if(_fileDao == null) {
          _fileDao = new FileDao_Impl(this);
        }
        return _fileDao;
      }
    }
  }
}
