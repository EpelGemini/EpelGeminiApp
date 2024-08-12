package com.epelgemini.core_ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ListAlt
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.SmartToy
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val icon: ImageVector,
    val title: String
) {
    Chat(
        icon = Icons.Rounded.SmartToy,
        title = "Safey"
    ),
    ReportList(
        icon = Icons.Filled.AccessTimeFilled,
        title = "Report List"
    ),
    JournalList(
        icon = Icons.Rounded.Description,
        title = "Emotion Journal"
    ),
    Activities(
        icon = Icons.AutoMirrored.Filled.ListAlt,
        title = "Activities"
    );

    companion object{
        fun fromRoute(route: String?): TopLevelDestination? =
            when (route?.substringBefore("/")) {
                Chat.name -> Chat
                ReportList.name -> ReportList
                JournalList.name -> JournalList
                Activities.name -> Activities
                else -> null
            }
    }
}