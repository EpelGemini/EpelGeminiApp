package com.epelgemini.journal_data.local.converter

import androidx.room.TypeConverter
import com.epelgemini.journal_domain.model.Feeling

class JournalConverters {

    @TypeConverter
    fun toFeeling(value: String) = enumValueOf<Feeling>(value)

    @TypeConverter
    fun fromFeeling(value: Feeling) = value.name
}