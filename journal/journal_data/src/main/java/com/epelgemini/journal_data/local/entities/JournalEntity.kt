package com.epelgemini.journal_data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class JournalEntity(
    @PrimaryKey val journalId: String = UUID.randomUUID().toString(),
    val title: String,
    val timestamp: Long
)
