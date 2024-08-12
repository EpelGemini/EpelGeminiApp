package com.epelgemini.journal_data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.epelgemini.journal_domain.model.Feeling
import java.util.UUID

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = JournalEntity::class,
            parentColumns = arrayOf("journalId"),
            childColumns = arrayOf("journalId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class WritingEntity(
    @PrimaryKey val writingId: String = UUID.randomUUID().toString(),
    @ColumnInfo(index = true)
    val journalId: String,
    val prompt: String?,
    val feeling: Feeling,
    val content: String
)
