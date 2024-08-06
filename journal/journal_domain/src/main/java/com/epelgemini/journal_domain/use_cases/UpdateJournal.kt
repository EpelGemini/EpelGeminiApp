package com.epelgemini.journal_domain.use_cases

import com.epelgemini.journal_domain.model.Journal
import com.epelgemini.journal_domain.model.ValidationException
import com.epelgemini.journal_domain.repository.JournalRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class UpdateJournal @Inject constructor(
    private val repository: JournalRepository
) {
    suspend operator fun invoke(
        journal: Journal
    ): Result<Unit> {
        if (journal.title.isEmpty()) {
            return Result.failure(
                ValidationException(
                    message = "Title can't be empty"
                )
            )
        }

        repository
            .updateJournal(
                journal = journal
            )
            .let {
                return Result.success(it)
            }
    }
}