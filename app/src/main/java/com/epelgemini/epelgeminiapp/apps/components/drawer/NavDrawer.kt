package com.epelgemini.epelgeminiapp.apps.components.drawer

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        windowInsets = WindowInsets(
            top = 32.dp
        )
    ) {
        TopLevelDestination.entries.forEach { destination ->
            NavigationDrawerItem(
                label = {
                    Text(text = destination.title)
                },
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