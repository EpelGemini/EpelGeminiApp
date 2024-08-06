package com.epelgemini.report_domain.use_cases

import com.epelgemini.report_domain.model.Report
import com.epelgemini.report_domain.repository.ReportRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DeleteReport @Inject constructor(
    private val repository: ReportRepository
) {
    suspend operator fun invoke(
        report: Report
    ) {
        repository
            .deleteReport(
                report = report
            )
    }
}