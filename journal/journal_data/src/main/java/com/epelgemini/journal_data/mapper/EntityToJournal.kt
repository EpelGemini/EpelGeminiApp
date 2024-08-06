package com.epelgemini.journal_data.mapper

import com.epelgemini.journal_data.local.entities.JournalAndWritings
import com.epelgemini.journal_domain.model.Journal
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun JournalAndWritings.toJournal(): Journal {
    val date = Instant.fromEpochMilliseconds(
        journal.timestamp
    ).toLocalDateTime(TimeZone.currentSystemDefault())

    return Journal(
        id = journal.journalId,
        title = journal.title,
        date = date,
        writings = writings.map { writing ->
            Journal.Writing(
                id = writing.writingId,
                prompt = writing.prompt,
                feeling = writing.feeling,
                content = writing.content
            )
        }
    )
}