package com.epelgemini.journal_domain.use_cases

import com.epelgemini.journal_domain.model.Journal
import com.epelgemini.core.utils.ValidationException
import com.epelgemini.journal_domain.repository.JournalRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CreateJournal @Inject constructor(
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

        if (journal.writings.isEmpty()) {
            return Result.failure(
                ValidationException(
                    message = "Journal can't be empty"
                )
            )
        }

        repository
            .createJournal(
                journal = journal
            )
            .let {
                return Result.success(it)
            }
    }
}