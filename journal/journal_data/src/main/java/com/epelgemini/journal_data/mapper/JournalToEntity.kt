package com.epelgemini.journal_data.mapper

import com.epelgemini.journal_data.local.entities.JournalEntity
import com.epelgemini.journal_data.local.entities.WritingEntity
import com.epelgemini.journal_domain.model.Journal
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

fun Journal.toJournalAndWritingsEntity(): Pair<JournalEntity, List<WritingEntity>> {
    var journalEntity = JournalEntity(
        title = title,
        timestamp = date
            .toInstant(TimeZone.currentSystemDefault())
            .toEpochMilliseconds()
    )

    id?.let { id ->
        journalEntity = journalEntity.copy(
            journalId = id
        )
    }

    val writingEntities = writings.map { writing ->
        var writingEntity = WritingEntity(
            journalId = journalEntity.journalId,
            prompt = writing.prompt,
            feeling = writing.feeling,
            content = writing.content
        )

        writing.id?.let { id ->
            writingEntity = writingEntity.copy(
                writingId = id
            )
        }

        writingEntity
    }

    return Pair(journalEntity, writingEntities)
}