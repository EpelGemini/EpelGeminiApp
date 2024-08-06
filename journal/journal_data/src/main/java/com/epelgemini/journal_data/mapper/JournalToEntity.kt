package com.epelgemini.journal_data.mapper

import com.epelgemini.journal_data.local.entities.JournalEntity
import com.epelgemini.journal_data.local.entities.WritingEntity
import com.epelgemini.journal_domain.model.Journal
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

fun Journal.toJournalAndWritingsEntity(): Pair<JournalEntity, List<WritingEntity>> {
    val journalEntity = JournalEntity(
        journalId = id,
        title = title,
        timestamp = date
            .toInstant(TimeZone.currentSystemDefault())
            .toEpochMilliseconds()
    )
    val writingEntities = writings.map { writing ->
        WritingEntity(
            writingId = writing.id,
            journalId = id,
            prompt = writing.prompt,
            feeling = writing.feeling,
            content = writing.content
        )
    }

    return Pair(journalEntity, writingEntities)
}