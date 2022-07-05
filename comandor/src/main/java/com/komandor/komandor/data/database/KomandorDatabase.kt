package com.komandor.komandor.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.komandor.komandor.data.database.KomandorDatabase.Companion.DB_VERSION
import com.komandor.komandor.data.database.model.Chat
import com.komandor.komandor.data.database.dao.ChatDao
import com.komandor.komandor.data.database.model.LocalCertificate
import com.komandor.komandor.data.database.dao.LocalCertificateDao
import com.komandor.komandor.data.database.model.Message
import com.komandor.komandor.data.database.dao.MessageDao
import com.komandor.komandor.data.database.model.User
import com.komandor.komandor.data.database.dao.UserDao
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(
    entities = [
        User::class,
        LocalCertificate::class,
        //Certificate::class,
        //Contact::class,
        Chat::class,
        Message::class,
        //File::class
    ],
    version = DB_VERSION,
    /*
    autoMigrations = [
        AutoMigration (
            from = 4,
            to = 5,
            spec = AppDatabase.MyAutoMigration::class
        )],

     */
    exportSchema = false
)

@TypeConverters(DateTypeConverter::class)
abstract class KomandorDatabase : RoomDatabase() {
    class MyAutoMigration : AutoMigrationSpec

    companion object {
        const val DB_VERSION = 17
        const val NAME = "app.db"
    }

    abstract fun userDao(): UserDao
    abstract fun localCertificateDao(): LocalCertificateDao
    abstract fun chatDao(): ChatDao
    abstract fun messageDao(): MessageDao


/*
    abstract fun certificateDao(): CertificateDao

    abstract fun fileDao(): FileDao

 */

}

fun buildAppDatabase(@ApplicationContext appContext: Context): KomandorDatabase {
    val MIGRATION_5_6 = object : Migration(5, 6) {
        override fun migrate(database: SupportSQLiteDatabase) {

        }
    }
    return Room.databaseBuilder(
        appContext,
        KomandorDatabase::class.java,
        KomandorDatabase.NAME
    ).addMigrations(MIGRATION_5_6)
        .fallbackToDestructiveMigration()
        .build()
}