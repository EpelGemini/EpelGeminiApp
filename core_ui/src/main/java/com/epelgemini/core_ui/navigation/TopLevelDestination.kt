package com.epelgemini.core_ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.DirectionsRun
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.material.icons.rounded.Forum
import androidx.compose.material.icons.rounded.Summarize
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val icon: ImageVector,
    val title: String
) {
    Chat(
        icon = Icons.Rounded.Forum,
        title = "Safey"
    ),
    ReportList(
        icon = Icons.Rounded.Summarize,
        title = "Report List"
    ),
    JournalList(
        icon = Icons.Rounded.EditNote,
        title = "Emotion Journal"
    ),
    Activities(
        icon = Icons.AutoMirrored.Rounded.DirectionsRun,
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