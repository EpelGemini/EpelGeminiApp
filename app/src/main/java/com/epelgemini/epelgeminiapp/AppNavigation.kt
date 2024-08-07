package com.epelgemini.epelgeminiapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.epelgemini.core_ui.navigation.TopLevelDestination
import com.epelgemini.epelgeminiapp.apps.components.drawer.NavDrawer
import com.epelgemini.epelgeminiapp.apps.state.AppState
import com.epelgemini.epelgeminiapp.apps.state.rememberAppState
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(
    appState: AppState = rememberAppState(),
) {
    val navController = appState.navController
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = appState.drawerState,
        drawerContent = {
            NavDrawer(
                currentDestination = appState.currentDestination,
                onSelectItem = { destination ->
                    navController.navigate(destination.name)
                    scope.launch {
                        appState.drawerState.close()
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                if (appState.shouldShowTopBar) {
                    TopAppBar(
                        title = { Text(text = "") },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        appState.drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Menu,
                                    contentDescription = "Toggle navigation drawer",
                                    tint = Color.White
                                )
                            }
                        }
                    )
                }
            }
        ) { contentPadding ->
            NavHostController(
                modifier = Modifier
                    .padding(contentPadding),
                navController = navController
            )
        }
    }
}

@Composable
private fun NavHostController(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = TopLevelDestination.Chat.name,
        modifier = modifier
    ) {
        composable(TopLevelDestination.Chat.name) {
            ChatView()
        }

        composable(TopLevelDestination.JournalList.name) {
            Text(text = "Journal List")
        }

        composable(TopLevelDestination.ReportList.name) {
            Text(text = "Report List")
        }
    }
}