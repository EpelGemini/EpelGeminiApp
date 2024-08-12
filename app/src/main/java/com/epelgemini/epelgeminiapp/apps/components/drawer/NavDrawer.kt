package com.epelgemini.epelgeminiapp.apps.components.drawer

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.epelgemini.core_ui.navigation.TopLevelDestination
import com.epelgemini.core_ui.ui.theme.EpelGeminiAppTheme

@Composable
fun NavDrawer(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination?,
    onSelectItem: (TopLevelDestination) -> Unit
) {
    val currentTopLevelDestination = TopLevelDestination.fromRoute(
        currentDestination?.route
    )

    ModalDrawerSheet(
        modifier = modifier,
        drawerShape = RectangleShape,
        windowInsets = WindowInsets(
            top = 32.dp,
            left = 16.dp,
            right = 16.dp
        )
    ) {
        TopLevelDestination.entries.forEach { destination ->
            NavigationDrawerItem(
                modifier = Modifier
                    .padding(
                        top = if (destination.ordinal == 0) 0.dp else 8.dp,
                        bottom = if (destination.ordinal == TopLevelDestination.entries.size-1) 0.dp else 8.dp
                    ),
                label = {
                    Text(
                        text = destination.title,
                        fontWeight = if (destination == TopLevelDestination.Chat) FontWeight.Bold else null
                    )
                },
                shape = RoundedCornerShape(16.dp),
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = MaterialTheme.colorScheme.primary,
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = MaterialTheme.colorScheme.primary
                ),
                selected = currentTopLevelDestination == destination,
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = "${destination.title}'s icon"
                    )
                },
                onClick = {
                    onSelectItem(destination)
                }
            )
        }
    }
}

@Preview
@Composable
private fun NavDrawerPreview() {
    EpelGeminiAppTheme {
        NavDrawer(
            currentDestination = null,
            onSelectItem = { _ -> }
        )
    }
}