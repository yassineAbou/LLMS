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
import org.yassineabou.playground.app.core.di.appModule
import org.yassineabou.playground.app.core.navigation.Screen
import org.yassineabou.playground.app.core.navigation.Screen.ChatHistoryScreen.ScreenSaver
import org.yassineabou.playground.app.core.navigation.listNavigationBarItems
import org.yassineabou.playground.app.core.theme.AppTheme
import org.yassineabou.playground.app.core.util.slideDownIn
import org.yassineabou.playground.app.core.util.slideDownOut
import org.yassineabou.playground.app.core.util.slideLeftIn
import org.yassineabou.playground.app.core.util.slideLeftOut
import org.yassineabou.playground.app.core.util.slideRightIn
import org.yassineabou.playground.app.core.util.slideRightOut
import org.yassineabou.playground.app.core.util.slideUpIn
import org.yassineabou.playground.app.core.util.slideUpOut
import org.yassineabou.playground.app.core.sharedViews.SnackbarControllerProvider
import org.yassineabou.playground.feature.Imagine.model.UrlExample
import org.yassineabou.playground.feature.Imagine.ui.supportingPane.SupportingPaneLayout
import org.yassineabou.playground.feature.Imagine.ui.FullScreenImage
import org.yassineabou.playground.feature.Imagine.ui.GeneratedImagesScreen
import org.yassineabou.playground.feature.Imagine.ui.ImageGenViewModel
import org.yassineabou.playground.feature.Imagine.ui.ImageProcessingScreen
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.chat.ui.chat.ChatScreen
import org.yassineabou.playground.feature.chat.ui.history.ChatHistoryScreen
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
    var isNavigationBarVisible by rememberSaveable { mutableStateOf(true) }
    var isFullScreenImage by rememberSaveable { mutableStateOf(false) }
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
            Screen.GeneratedImagesScreen.route,
            Screen.FullScreenImage.route,
            Screen.ImageProcessingScreen.route,
            Screen.ChatHistoryScreen.route,
        )
        isFullScreenImage = navBackStackEntry?.destination?.route?.let { currentRoute ->
            routeToCheck.any { currentRoute.startsWith(it) }
        } ?: false
        isNavigationBarVisible = !isFullScreenImage && !isSelectionMode
    }

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    // Customize the layout type based on window size class and isBottomBarVisible
    val layoutType = when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            if (isNavigationBarVisible) NavigationSuiteType.NavigationBar else NavigationSuiteType.None
        }
        WindowWidthSizeClass.MEDIUM -> {
            if (isNavigationBarVisible) NavigationSuiteType.NavigationBar else NavigationSuiteType.None
        }
        WindowWidthSizeClass.EXPANDED -> NavigationSuiteType.NavigationRail
        else -> NavigationSuiteType.NavigationBar
    }

    SnackbarControllerProvider { host ->
        NavigationSuiteScaffold(
            navigationSuiteItems = {
                listNavigationBarItems.forEach { item ->
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
            ) {
                composable(Screen.ChatScreen.route) {
                    ChatScreen(navController = navController, chatViewModel = chatViewModel)
                }
                composable(
                    route = Screen.ChatHistoryScreen.route,
                    enterTransition = slideUpIn(),
                    exitTransition = slideDownOut(),
                    popEnterTransition = slideDownIn(),
                    popExitTransition = slideUpOut()
                ) {
                    ChatHistoryScreen(navController = navController, chatViewModel = chatViewModel)
                }
                composable(Screen.ImagineScreen.route) {
                    SupportingPaneLayout(imageGenViewModel = imageGenViewModel, navController = navController)
                }
                composable(
                    route = Screen.ImageProcessingScreen.route,
                    enterTransition = slideLeftIn(),
                    exitTransition = slideRightOut(),
                    popEnterTransition = slideLeftIn(),
                    popExitTransition = slideRightOut()
                ) {
                    ImageProcessingScreen(navController = navController, imageGenViewModel = imageGenViewModel)
                }
                composable(
                    route = Screen.FullScreenImage.route,
                    enterTransition = slideLeftIn(),
                    exitTransition = slideRightOut(),
                    popEnterTransition = slideRightIn(),
                    popExitTransition = slideLeftOut()
                ) {
                    FullScreenImage(imageGenViewModel = imageGenViewModel, navController = navController)
                }
                composable(
                    route = Screen.GeneratedImagesScreen.route,
                    enterTransition = slideLeftIn(),
                    exitTransition = slideRightOut(),
                    popEnterTransition = slideRightIn(),
                    popExitTransition = slideLeftOut()
                ) {
                    GeneratedImagesScreen(imageGenViewModel = imageGenViewModel, navController = navController)
                }
                composable(Screen.Profile.route) {
                    ProfileContent()
                }
            }
        }
    }
}


