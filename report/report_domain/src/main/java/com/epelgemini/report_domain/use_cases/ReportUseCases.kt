package com.epelgemini.report_domain.use_cases

data class ReportUseCases(
    val createOrUpdateReport: CreateOrUpdateReport,
    val deleteReport: DeleteReport,
    val getReports: GetReports
)
