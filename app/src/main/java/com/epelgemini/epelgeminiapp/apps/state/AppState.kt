package com.epelgemini.epelgeminiapp.apps.state

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.epelgemini.core_ui.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AppState(
    val drawerState: DrawerState,
    val snackbarHostState: SnackbarHostState,
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope
) {
    fun showSnackBar(message: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val shouldShowTopBar: Boolean
        @Composable get() = when (currentDestination?.route) {
            TopLevelDestination.Chat.name -> true
            TopLevelDestination.ReportList.name -> true
            TopLevelDestination.JournalList.name -> true
            //TopLevelDestination.Activities.name -> true
            else -> false
        }
}

@Composable
fun rememberAppState(
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): AppState = remember(drawerState, snackbarHostState, navController) {
    AppState(
        drawerState,
        snackbarHostState,
        navController,
        coroutineScope
    )
}