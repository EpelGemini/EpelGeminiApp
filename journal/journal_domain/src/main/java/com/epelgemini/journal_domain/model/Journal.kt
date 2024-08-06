package com.epelgemini.journal_domain.model

import kotlinx.datetime.LocalDateTime

data class Journal(
    val id: String?,
    val title: String,
    val date: LocalDateTime,
    val writings: List<Writing>
) {
    data class Writing(
        val id: String?,
        val prompt: String?,
        val feeling: Feeling,
        val content: String
    )
}
