package com.epelgemini.report_data.mapper

import com.epelgemini.report_data.local.entities.ReportEntity
import com.epelgemini.report_domain.model.Report

fun ReportEntity.toReport(): Report {
    return Report(
        reportId = reportId,
        category = category,
        reporter = reporter,
        gender = gender,
        phoneNumberOrEmail = phoneNumberOrEmail,
        domicile = domicile,
        disabilityStatus = disabilityStatus,
        disability = disability,
        perpetratorName = perpetratorName,
        perpetratorGender = perpetratorGender,
        perpetratorDomicile = perpetratorDomicile,
        chronology = chronology,
        evidencePaths = evidencePaths
    )
}