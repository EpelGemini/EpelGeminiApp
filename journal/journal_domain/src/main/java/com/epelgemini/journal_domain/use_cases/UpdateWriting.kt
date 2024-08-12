package com.epelgemini.journal_domain.use_cases

import com.epelgemini.journal_domain.model.Journal
import com.epelgemini.core.utils.ValidationException
import com.epelgemini.journal_domain.repository.JournalRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class UpdateWriting @Inject constructor(
    private val repository: JournalRepository
) {
    suspend operator fun invoke(
        journalId: String,
        writing: Journal.Writing
    ): Result<Unit> {
        if (writing.content.isEmpty()) {
            return Result.failure(
                ValidationException(
                    message = "Content can't be empty"
                )
            )
        }

        repository
            .updateWriting(
                journalId = journalId,
                writing = writing
            )
            .let {
                return Result.success(it)
            }
    }
}