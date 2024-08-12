package com.epelgemini.epelgeminiapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.epelgemini.core_ui.navigation.Route
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
    val topBarTitle = TopLevelDestination.fromRoute(
        appState.currentDestination?.route
    )?.title ?: TopLevelDestination.Chat.title

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
                        title = {
                            Text(topBarTitle, color = Color.White, fontWeight = FontWeight.Bold)
                        },
                        navigationIcon = {
//                            IconButton(
//                                onClick = {
//                                    scope.launch {
//                                        appState.drawerState.apply {
//                                            if (isClosed) open() else close()
//                                        }
//                                    }
//                                }
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Rounded.Menu,
//                                    contentDescription = "Toggle navigation drawer",
//                                    tint = Color.White
//                                )
//                            }
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
                                    Icons.Default.Menu,
                                    contentDescription = "Menu",
                                    tint = Color.White
                                )
                            }
                        },
                        backgroundColor = PrimaryPurple,
                        elevation = 0.dp
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
    val chatVM: ChatViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = TopLevelDestination.Chat.name,
        modifier = modifier
    ) {
        composable(TopLevelDestination.Chat.name) {
            ChatView(
                viewModel = chatVM
            )
        }

        composable(TopLevelDestination.JournalList.name) {
            JournalView(
                onAddClick = {
                    navController.navigate(Route.CreateJournal.name)
                }
            )
        }

        composable(Route.CreateJournal.name) {
            NewJournalEntryView(
                onBackClicked = {
                    navController.navigateUp()
                },
                onSaveClicked = {
                    navController.navigateUp()
                }
            )
        }

        composable(TopLevelDestination.ReportList.name) {
            ListLaporan(
                onAddClick = {
                    navController.navigate(Route.CreateReportOne.name)
                }
            )
        }

        composable(Route.CreateReportOne.name) {
            FirstPhaseReportCase(
                onBackClicked = {
                    navController.navigateUp()
                },
                onNext = {
                    navController.navigate(Route.CreateReportTwo.name)
                }
            )
        }

        composable(Route.CreateReportTwo.name) {
            SecondPhaseReportCases(
                onBackClicked = {
                    navController.navigateUp()
                },
                onNext = {
                    navController.navigate(Route.CreateReportThree.name)
                }
            )
        }

        composable(Route.CreateReportThree.name) {
            ThirdPhaseReportCases(
                onBackClicked = {
                    navController.navigateUp()
                },
                onFinish = {
                    navController.popBackStack(
                        route = TopLevelDestination.ReportList.name,
                        inclusive = false
                    )
                }
            )
        }
    }
}