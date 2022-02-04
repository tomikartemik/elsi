package com.komandor.komandor.data.database;

import com.komandor.komandor.data.database.certificates.Certificate;
import com.komandor.komandor.data.database.certificates.CertificateDao;
import com.komandor.komandor.data.database.chats.Chat;
import com.komandor.komandor.data.database.chats.ChatDao;
import com.komandor.komandor.data.database.contacts.Contact;
import com.komandor.komandor.data.database.contacts.ContactDao;
import com.komandor.komandor.data.database.contacts.ContactsCertificatesDao;
import com.komandor.komandor.data.database.files.File;
import com.komandor.komandor.data.database.files.FileDao;
import com.komandor.komandor.data.database.messages.Message;
import com.komandor.komandor.data.database.messages.MessageDao;
import com.komandor.komandor.data.database.users.User;
import com.komandor.komandor.data.database.users.UserDao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SimpleSQLiteQuery;

@Database(entities = {User.class, Certificate.class, Contact.class, Chat.class, Message.class, File.class}, version = 2, exportSchema = false)
public abstract class KomandorDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract CertificateDao certificateDao();

    public abstract ContactDao contactDao();

    public abstract ContactsCertificatesDao contactsCertificatesDao();

    public abstract ChatDao chatDao();

    public abstract MessageDao messageDao();

    public abstract FileDao fileDao();

    public void clearDB() {
        SimpleSQLiteQuery simpleSQLiteQuery = new SimpleSQLiteQuery("DELETE FROM sqlite_sequence");

        beginTransaction();
        clearAllTables();
        query(simpleSQLiteQuery);
        setTransactionSuccessful();
        endTransaction();
    }

}
