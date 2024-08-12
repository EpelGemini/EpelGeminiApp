package com.epelgemini.report_data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.epelgemini.report_data.local.converter.ReportConverter
import com.epelgemini.report_data.local.dao.ReportDao
import com.epelgemini.report_data.local.entities.ReportEntity

@TypeConverters(ReportConverter::class)
@Database(
    entities = [
        ReportEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ReportDatabase : RoomDatabase() {
    abstract fun reportDao(): ReportDao
}