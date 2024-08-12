package com.epelgemini.report_data.di

import com.epelgemini.report_data.repository.ReportRepositoryImpl
import com.epelgemini.report_domain.repository.ReportRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        ReportDatabaseModule::class
    ]
)
@InstallIn(SingletonComponent::class)
abstract class ReportRepositoryModule {
    @Binds
    abstract fun provideRepository(repository: ReportRepositoryImpl): ReportRepository
}