package com.epelgemini.report_domain.model

data class ReportValidationResult(
    val success: Boolean,
    val reporterError: String? = null,
    val phoneNumberOrEmailError: String? = null,
    val domicileError: String? = null,
    val disabilityError: String? = null,
    val perpetratorNameError: String? = null,
    val perpetratorDomicileError: String? = null,
    val chronologyError: String? = null
)
