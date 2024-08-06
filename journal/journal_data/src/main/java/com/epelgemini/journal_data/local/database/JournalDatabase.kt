package com.epelgemini.journal_data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.epelgemini.journal_data.local.converter.JournalConverters
import com.epelgemini.journal_data.local.dao.JournalDao
import com.epelgemini.journal_data.local.entities.JournalEntity
import com.epelgemini.journal_data.local.entities.WritingEntity

@TypeConverters(JournalConverters::class)
@Database(
    entities = [
        JournalEntity::class, WritingEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class JournalDatabase : RoomDatabase() {
    abstract fun journalDao(): JournalDao
}