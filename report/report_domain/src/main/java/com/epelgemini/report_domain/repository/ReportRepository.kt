package com.epelgemini.report_domain.repository

import com.epelgemini.report_domain.model.Report
import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    suspend fun createOrUpdateReport(
        report: Report
    )

    suspend fun deleteReport(
        report: Report
    )

    fun getReports(): Flow<List<Report>>
}