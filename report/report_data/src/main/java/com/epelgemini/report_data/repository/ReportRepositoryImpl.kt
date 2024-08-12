package com.epelgemini.report_data.repository

import com.epelgemini.report_data.local.source.ReportLocalDataSource
import com.epelgemini.report_data.mapper.toEntity
import com.epelgemini.report_data.mapper.toReport
import com.epelgemini.report_domain.model.Report
import com.epelgemini.report_domain.repository.ReportRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportRepositoryImpl @Inject constructor(
    private val localDataSource: ReportLocalDataSource
): ReportRepository {
    override suspend fun createOrUpdateReport(report: Report) {
        val entity = report.toEntity()

        localDataSource
            .createOrUpdateReport(
                report = entity
            )
    }

    override suspend fun deleteReport(report: Report) {
        val entity = report.toEntity()

        localDataSource
            .deleteReport(
                report = entity
            )
    }

    override fun getReports(): Flow<List<Report>> {
        return localDataSource
            .getReports()
            .map { reports ->
                reports.map {
                    it.toReport()
                }
            }
    }
}