package com.epelgemini.report_data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.epelgemini.core.domain.model.Gender
import com.epelgemini.report_domain.model.ReportCategory
import java.util.UUID

@Entity
data class ReportEntity(
    @PrimaryKey val reportId: String = UUID.randomUUID().toString(),
    val category: ReportCategory,
    val reporter: String,
    val gender: Gender,
    val phoneNumberOrEmail: String,
    val domicile: String,
    val disabilityStatus: Boolean,
    val disability: String?,
    val perpetratorName: String,
    val perpetratorGender: Gender,
    val perpetratorDomicile: String,
    val chronology: String,
    val evidencePaths: List<String>
)