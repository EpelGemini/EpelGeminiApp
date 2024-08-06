package com.epelgemini.journal_data.repository

import com.epelgemini.journal_data.local.source.JournalLocalDataSource
import com.epelgemini.journal_data.mapper.toEntity
import com.epelgemini.journal_data.mapper.toJournal
import com.epelgemini.journal_data.mapper.toJournalAndWritingsEntity
import com.epelgemini.journal_domain.model.Journal
import com.epelgemini.journal_domain.repository.JournalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JournalRepositoryImpl @Inject constructor(
    private val localDataSource: JournalLocalDataSource
): JournalRepository {
    override suspend fun createJournal(journal: Journal) {
        val entity = journal.toJournalAndWritingsEntity()

        localDataSource
            .createJournal(
                journal = entity.first,
                writings = entity.second
            )
    }

    override suspend fun updateJournal(journal: Journal) {
        val entity = journal.toJournalAndWritingsEntity()

        localDataSource
            .updateJournal(
                journal = entity.first
            )
    }

    override suspend fun updateWriting(journalId: String, writing: Journal.Writing) {
        val entity = writing.toEntity(
            journalId = journalId
        )

        localDataSource
            .updateWriting(
                writing = entity
            )
    }

    override suspend fun deleteJournal(journal: Journal) {
        val entity = journal.toJournalAndWritingsEntity()

        localDataSource
            .deleteJournal(
                journal = entity.first
            )
    }

    override fun getJournals(): Flow<List<Journal>> {
        return localDataSource
            .getJournals()
            .map { journals ->
                journals.map {
                    it.toJournal()
                }
            }
    }
}