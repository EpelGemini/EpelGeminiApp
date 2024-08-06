package com.epelgemini.journal_domain.di

import com.epelgemini.journal_domain.repository.JournalRepository
import com.epelgemini.journal_domain.use_cases.CreateJournal
import com.epelgemini.journal_domain.use_cases.DeleteJournal
import com.epelgemini.journal_domain.use_cases.GetJournals
import com.epelgemini.journal_domain.use_cases.JournalUseCases
import com.epelgemini.journal_domain.use_cases.UpdateJournal
import com.epelgemini.journal_domain.use_cases.UpdateWriting
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object JournalUseCaseModule {
    @ViewModelScoped
    @Provides
    fun provideJournalUseCases(
        repository: JournalRepository
    ): JournalUseCases {
        return JournalUseCases(
            createJournal = CreateJournal(
                repository = repository
            ),
            updateJournal = UpdateJournal(
                repository = repository
            ),
            updateWriting = UpdateWriting(
                repository = repository
            ),
            deleteJournal = DeleteJournal(
                repository = repository
            ),
            getJournals = GetJournals(
                repository = repository
            )
        )
    }
}