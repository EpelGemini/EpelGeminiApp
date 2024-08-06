package com.epelgemini.report_domain.use_cases

import com.epelgemini.report_domain.model.Report
import com.epelgemini.report_domain.repository.ReportRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetReports @Inject constructor(
    private val repository: ReportRepository
) {
    operator fun invoke(): Flow<List<Report>> {
        return repository
            .getReports()
    }
}
