package com.epelgemini.report_data.di

import android.content.Context
import androidx.room.Room
import com.epelgemini.report_data.BuildConfig
import com.epelgemini.report_data.local.database.ReportDatabase
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
class ReportDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ReportDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(BuildConfig.SQL_PASSWORD.toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            ReportDatabase::class.java,
            "report.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

}