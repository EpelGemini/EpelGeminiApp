package com.epelgemini.report_domain.use_cases

import com.epelgemini.report_domain.model.Report
import com.epelgemini.report_domain.model.ReportValidationResult
import com.epelgemini.report_domain.repository.ReportRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CreateOrUpdateReport @Inject constructor(
    private val repository: ReportRepository
) {
    suspend operator fun invoke(
        report: Report
    ): ReportValidationResult {
        if (report.reporter.isEmpty()) {
            return ReportValidationResult(
                success = false,
                reporterError = "Reporter name can't be empty"
            )
        }

        if (report.phoneNumberOrEmail.isEmpty()) {
            return ReportValidationResult(
                success = false,
                phoneNumberOrEmailError = "Phone Number / Email can't be empty"
            )
        }

        if (
            report.phoneNumberOrEmail.startsWith("+62") ||
            report.phoneNumberOrEmail.startsWith("08")
            ) {
            val alphabetsAndSpecialCharRegex = "[A-Za-z!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex()
            if (report.phoneNumberOrEmail.contains(alphabetsAndSpecialCharRegex)){
                return ReportValidationResult(
                    success = false,
                    phoneNumberOrEmailError = "Phone Number can't contain character"
                )
            }

            val numberCount = if (report.phoneNumberOrEmail.startsWith("+")) {
                report.phoneNumberOrEmail.count() - 1
            } else {
                report.phoneNumberOrEmail.count()
            }
            if (numberCount < 10 || numberCount > 13){
                return ReportValidationResult(
                    success = false,
                    phoneNumberOrEmailError = "Phone Number is invalid"
                )
            }
        }

        val emailAddressRegex = Regex(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
        if (!report.phoneNumberOrEmail.matches(emailAddressRegex)) {
            return ReportValidationResult(
                success = false,
                phoneNumberOrEmailError = "Email is invalid"
            )
        }

        if (report.domicile.isEmpty()) {
            return ReportValidationResult(
                success = false,
                domicileError = "Domicile can't be empty"
            )
        }

        if (report.disabilityStatus && report.disability.isNullOrEmpty()) {
            return ReportValidationResult(
                success = false,
                disabilityError = "Disability description can't be empty"
            )
        }

        if (report.perpetratorName.isEmpty()) {
            return ReportValidationResult(
                success = false,
                perpetratorNameError = "Perpetrator name can't be empty"
            )
        }

        if (report.perpetratorDomicile.isEmpty()) {
            return ReportValidationResult(
                success = false,
                perpetratorDomicileError = "Perpetrator domicile can't be empty"
            )
        }

        if (report.chronology.isEmpty()) {
            return ReportValidationResult(
                success = false,
                chronologyError = "Chronology can't be empty"
            )
        }

        repository
            .createOrUpdateReport(
                report = report
            )
            .let {
                return ReportValidationResult(
                    success = true
                )
            }
    }
}