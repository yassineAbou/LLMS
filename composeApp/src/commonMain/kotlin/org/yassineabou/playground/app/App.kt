package org.yassineabou.playground.app

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.dragselectcompose.core.rememberDragSelectState
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.app.di.appModule
import org.yassineabou.playground.app.ui.navigation.Screen
import org.yassineabou.playground.app.ui.navigation.Screen.ChatHistoryScreen.ScreenSaver
import org.yassineabou.playground.app.ui.navigation.listBottomBarItems
import org.yassineabou.playground.app.ui.theme.AppTheme
import org.yassineabou.playground.app.ui.util.slideInFromLeft
import org.yassineabou.playground.app.ui.util.slideInFromRight
import org.yassineabou.playground.app.ui.util.slideOutToLeft
import org.yassineabou.playground.app.ui.util.slideOutToRight
import org.yassineabou.playground.app.ui.view.SnackbarControllerProvider
import org.yassineabou.playground.feature.Imagine.model.UrlExample
import org.yassineabou.playground.feature.Imagine.supportingPane.SupportingPaneLayout
import org.yassineabou.playground.feature.Imagine.ui.FullScreenImage
import org.yassineabou.playground.feature.Imagine.ui.GeneratedImagesScreen
import org.yassineabou.playground.feature.Imagine.ui.ImageGenViewModel
import org.yassineabou.playground.feature.Imagine.ui.ImageProcessingScreen
import org.yassineabou.playground.feature.chat.ui.chat.ChatScreen
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.chat.ui.history.ChatHistoryScreen
import org.yassineabou.playground.feature.chat.ui.history.SearchHistoryScreen
import org.yassineabou.playground.feature.profile.ui.ProfileContent

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule())
    }) {
        AppTheme {
            LLMsApp()
        }
    }
}

@Composable
fun LLMsApp() {
    val navController = rememberNavController()
    var isBottomBarVisible by rememberSaveable { (mutableStateOf(true)) }
    var isFullScreenImage by rememberSaveable { (mutableStateOf(true)) }
    val dragSelectState = rememberDragSelectState<UrlExample>(compareSelector = { it.id })
    val isSelectionMode = dragSelectState.inSelectionMode
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val chatViewModel = koinViewModel<ChatViewModel>()
    val imageGenViewModel = koinViewModel<ImageGenViewModel>()

    // Track the current destination
    var currentDestination by rememberSaveable(stateSaver = ScreenSaver) {
        mutableStateOf(Screen.ChatScreen)
    }

    LaunchedEffect(navBackStackEntry?.destination?.route, isSelectionMode) {

        val routeToCheck = listOf(
            Screen.FullScreenImage.route,
            Screen.ImageProcessingScreen.route,
            Screen.ChatHistoryScreen.route,
            Screen.GeneratedImagesScreen.route,
            Screen.SearchHistoryScreen.route
        )

        isFullScreenImage = navBackStackEntry?.destination?.route?.let { currentRoute ->
            routeToCheck.any { currentRoute.startsWith(it) }
        } ?: false

        isBottomBarVisible = !isFullScreenImage and !isSelectionMode
    }

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    // Customize the layout type based on window size class
    val layoutType = when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> NavigationSuiteType.NavigationBar
        WindowWidthSizeClass.MEDIUM -> NavigationSuiteType.NavigationBar // Force bottom bar for medium
        WindowWidthSizeClass.EXPANDED -> NavigationSuiteType.NavigationRail // Use rail for expanded
        else -> NavigationSuiteType.NavigationBar // Fallback to bottom bar
    }



    SnackbarControllerProvider { host ->
        // Use NavigationSuiteScaffold for adaptive navigation
        NavigationSuiteScaffold(
            navigationSuiteItems = {
                listBottomBarItems.forEach { item ->
                    item(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        },
                        label = { Text(text = item.name) },
                        selected = currentDestination.route == item.route,
                        onClick = {
                            currentDestination = Screen.fromRoute(item.route)
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            },
            layoutType = layoutType
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.ChatScreen.route,
                //modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.ChatScreen.route) {
                    ChatScreen(navController = navController, chatViewModel = chatViewModel)
                }
                composable(Screen.ChatHistoryScreen.route) {
                    ChatHistoryScreen(navController = navController, chatViewModel = chatViewModel)
                }
                composable(Screen.SearchHistoryScreen.route) {
                    SearchHistoryScreen(navController = navController)
                }
                composable(Screen.ImagineScreen.route) {
                    SupportingPaneLayout(imageGenViewModel = imageGenViewModel, navController = navController)
                }
                composable(
                    route = Screen.ImageProcessingScreen.route,
                    enterTransition = { slideInFromRight() },
                    exitTransition = { slideOutToLeft() },
                    popEnterTransition = { slideInFromLeft() },
                    popExitTransition = { slideOutToRight() }
                ) {
                    ImageProcessingScreen(navController = navController, imageGenViewModel = imageGenViewModel)
                }
                composable(
                    route = Screen.FullScreenImage.route,
                    enterTransition = { slideInFromRight() },
                    exitTransition = { slideOutToLeft() },
                    popEnterTransition = { slideInFromLeft() },
                    popExitTransition = { slideOutToRight() }
                ) {
                    FullScreenImage(imageGenViewModel = imageGenViewModel, navController = navController)
                }
                composable(
                    route = Screen.GeneratedImagesScreen.route,
                    enterTransition = { slideInFromRight() },
                    exitTransition = { slideOutToLeft() },
                    popEnterTransition = { slideInFromLeft() },
                    popExitTransition = { slideOutToRight() }
                ) {
                    GeneratedImagesScreen(imageGenViewModel = imageGenViewModel, navController = navController)
                }
                composable(Screen.Profile.route) {
                    ProfileContent(navController = navController)
                }
            }
        }
    }
}

