package com.epelgemini.report_data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.epelgemini.report_data.local.entities.ReportEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReport(report: ReportEntity)

    @Delete
    suspend fun deleteReport(report: ReportEntity)

    @Transaction
    @Query("SELECT * FROM ReportEntity")
    fun getReports(): Flow<List<ReportEntity>>
}