package com.epelgemini.report_domain.model

import com.epelgemini.core.domain.model.Gender

data class Report(
    val reportId: String?,
    val category: ReportCategory,
    val reporter: String,
    val gender: Gender,
    val phoneNumberOrEmail: String,
    val domicile: String,
    val disabilityStatus: Boolean,
    val disability: String?,
    val perpetratorName: String,
    val perpetratorGender: Gender,
    val perpetratorDomicile: String,
    val chronology: String,
    val evidencePaths: List<String>
)
