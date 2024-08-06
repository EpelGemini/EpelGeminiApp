package com.epelgemini.journal_data.di

import com.epelgemini.journal_data.repository.JournalRepositoryImpl
import com.epelgemini.journal_domain.repository.JournalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        JournalDatabaseModule::class
    ]
)
@InstallIn(SingletonComponent::class)
abstract class JournalRepositoryModule {
    @Binds
    abstract fun provideRepository(repository: JournalRepositoryImpl): JournalRepository
}