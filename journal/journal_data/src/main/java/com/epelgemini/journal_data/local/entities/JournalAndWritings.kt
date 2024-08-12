package com.epelgemini.journal_data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class JournalAndWritings(
    @Embedded val journal: JournalEntity,
    @Relation(
        parentColumn = "journalId",
        entityColumn = "journalId"
    )
    val writings: List<WritingEntity>
)
