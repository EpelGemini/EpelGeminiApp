package com.epelgemini.journal_domain.use_cases

import com.epelgemini.journal_domain.model.Journal
import com.epelgemini.journal_domain.repository.JournalRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetJournals @Inject constructor(
    private val repository: JournalRepository
) {
    operator fun invoke(): Flow<List<Journal>> {
        return repository
            .getJournals()
    }
}