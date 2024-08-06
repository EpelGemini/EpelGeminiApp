package com.epelgemini.journal_data.di

import android.content.Context
import androidx.room.Room
import com.epelgemini.journal_data.BuildConfig
import com.epelgemini.journal_data.local.database.JournalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class JournalDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): JournalDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.SQL_PASSWORD.toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            JournalDatabase::class.java,
            "journal.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

}