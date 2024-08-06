package com.epelgemini.journal_data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.epelgemini.journal_data.local.entities.JournalAndWritings
import com.epelgemini.journal_data.local.entities.JournalEntity
import com.epelgemini.journal_data.local.entities.WritingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJournal(journal: JournalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWritings(writings: List<WritingEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWriting(writing: WritingEntity)

    @Delete
    suspend fun deleteJournal(journal: JournalEntity)

    @Transaction
    suspend fun createJournal(
        journal: JournalEntity,
        writings: List<WritingEntity>
    ) {
        insertJournal(journal)
        insertWritings(writings)
    }

    @Transaction
    @Query("SELECT * FROM JournalEntity")
    fun getJournals(): Flow<List<JournalAndWritings>>
}