package com.epelgemini.journal_data.local.source

import com.epelgemini.journal_data.local.database.JournalDatabase
import com.epelgemini.journal_data.local.entities.JournalAndWritings
import com.epelgemini.journal_data.local.entities.JournalEntity
import com.epelgemini.journal_data.local.entities.WritingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JournalLocalDataSource @Inject constructor(
    db: JournalDatabase
) {
    private val journalDao = db.journalDao()

    suspend fun createJournal(
        journal: JournalEntity,
        writings: List<WritingEntity>
    ) {
        journalDao
            .createJournal(
                journal = journal,
                writings = writings
            )
    }

    suspend fun updateJournal(
        journal: JournalEntity
    ) {
        journalDao
            .insertJournal(journal = journal)
    }

    suspend fun updateWriting(
        writing: WritingEntity
    ) {
        journalDao
            .insertWriting(writing = writing)
    }

    suspend fun deleteJournal(
        journal: JournalEntity
    ) {
        journalDao
            .deleteJournal(journal = journal)
    }

    fun getJournals(): Flow<List<JournalAndWritings>> {
        return journalDao
            .getJournals()
    }
}