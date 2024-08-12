package com.epelgemini.journal_domain.repository

import com.epelgemini.journal_domain.model.Journal
import kotlinx.coroutines.flow.Flow

interface JournalRepository {
    suspend fun createJournal(
        journal: Journal
    )

    suspend fun updateJournal(
        journal: Journal
    )

    suspend fun updateWriting(
        journalId: String,
        writing: Journal.Writing
    )

    suspend fun deleteJournal(
        journal: Journal
    )

    fun getJournals(): Flow<List<Journal>>
}