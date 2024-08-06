package com.epelgemini.report_data.local.source

import com.epelgemini.report_data.local.database.ReportDatabase
import com.epelgemini.report_data.local.entities.ReportEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportLocalDataSource @Inject constructor(
    db: ReportDatabase
) {
    private val reportDao = db.reportDao()

    suspend fun createOrUpdateReport(
        report: ReportEntity
    ) {
        reportDao
            .insertReport(
                report = report
            )
    }

    suspend fun deleteReport(
        report: ReportEntity
    ) {
        reportDao
            .deleteReport(report = report)
    }

    fun getReports(): Flow<List<ReportEntity>> {
        return reportDao
            .getReports()
    }
}