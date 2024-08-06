package com.epelgemini.report_domain.di

import com.epelgemini.report_domain.repository.ReportRepository
import com.epelgemini.report_domain.use_cases.CreateOrUpdateReport
import com.epelgemini.report_domain.use_cases.DeleteReport
import com.epelgemini.report_domain.use_cases.GetReports
import com.epelgemini.report_domain.use_cases.ReportUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ReportUseCaseModule {
    @ViewModelScoped
    @Provides
    fun provideReportUseCases(
        repository: ReportRepository
    ): ReportUseCases {
        return ReportUseCases(
            createOrUpdateReport = CreateOrUpdateReport(
                repository = repository
            ),
            deleteReport = DeleteReport(
                repository = repository
            ),
            getReports = GetReports(
                repository = repository
            )
        )
    }
}