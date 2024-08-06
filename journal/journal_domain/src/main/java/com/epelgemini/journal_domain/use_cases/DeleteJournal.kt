package com.epelgemini.journal_domain.use_cases

import com.epelgemini.journal_domain.model.Journal
import com.epelgemini.journal_domain.repository.JournalRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DeleteJournal @Inject constructor(
    private val repository: JournalRepository
) {
    suspend operator fun invoke(
        journal: Journal
    ) {
        repository
            .deleteJournal(
                journal = journal
            )
    }
}