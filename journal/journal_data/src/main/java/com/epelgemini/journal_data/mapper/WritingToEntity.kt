package com.epelgemini.journal_data.mapper

import com.epelgemini.journal_data.local.entities.WritingEntity
import com.epelgemini.journal_domain.model.Journal

fun Journal.Writing.toEntity(
    journalId: String
): WritingEntity {
    var entity = WritingEntity(
        journalId = journalId,
        prompt = prompt,
        feeling = feeling,
        content = content
    )

    id?.let { id ->
        entity = entity
            .copy(
                writingId = id
            )
    }

    return entity
}