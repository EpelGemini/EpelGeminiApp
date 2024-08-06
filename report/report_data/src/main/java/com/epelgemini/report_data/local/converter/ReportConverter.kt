package com.epelgemini.report_data.local.converter

import androidx.room.TypeConverter
import com.epelgemini.core.domain.model.Gender
import com.epelgemini.report_domain.model.ReportCategory
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ReportConverter {
    @TypeConverter
    fun toGender(value: String) = enumValueOf<Gender>(value)

    @TypeConverter
    fun fromGender(value: Gender) = value.name

    @TypeConverter
    fun toCategory(value: String) = enumValueOf<ReportCategory>(value)

    @TypeConverter
    fun fromCategory(value: ReportCategory) = value.name

    @TypeConverter
    fun fromList(value: List<String>) = Json.encodeToString(value = value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String>>(value)
}