package org.yassineabou.llms.app

import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.llms.app.core.di.appModules
import org.yassineabou.llms.app.core.navigation.Screen
import org.yassineabou.llms.app.core.navigation.Screen.ChatHistoryScreen.ScreenSaver
import org.yassineabou.llms.app.core.navigation.listNavigationBarItems
import org.yassineabou.llms.app.core.sharedViews.SnackbarControllerProvider
import org.yassineabou.llms.app.core.theme.AppTheme
import org.yassineabou.llms.app.core.util.NavTransitions
import org.yassineabou.llms.feature.chat.ui.ChatViewModel
import org.yassineabou.llms.feature.chat.ui.chat.ChatScreen
import org.yassineabou.llms.feature.chat.ui.history.ChatHistoryScreen
import org.yassineabou.llms.feature.imagine.ui.FullScreenImage
import org.yassineabou.llms.feature.imagine.ui.GeneratedImagesScreen
import org.yassineabou.llms.feature.imagine.ui.ImageGenViewModel
import org.yassineabou.llms.feature.imagine.ui.ImageGenerationLoadingScreen
import org.yassineabou.llms.feature.imagine.ui.supportingPane.SupportingPaneLayout
import org.yassineabou.llms.feature.imagine.ui.supportingPane.rememberSupportingPaneNavigator

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModules())
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
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val chatViewModel = koinViewModel<ChatViewModel>()
    val imageGenViewModel = koinViewModel<ImageGenViewModel>()
    val supportingPaneNavigator = rememberSupportingPaneNavigator()

    // Track the current destination
    var currentDestination by rememberSaveable(stateSaver = ScreenSaver) {
        mutableStateOf(Screen.ChatScreen)
    }

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    var isLargeScreen by  remember { mutableStateOf(windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) }

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

    LaunchedEffect(navBackStackEntry?.destination?.route) {
        isLargeScreen =! isLargeScreen
        val routeToCheck = listOf(
            Screen.GeneratedImagesScreen.route,
            Screen.FullScreenImage.route,
            Screen.ImageGenerationLoadingScreen.route,
            Screen.ChatHistoryScreen.route,
        )
        isFullScreenImage = navBackStackEntry?.destination?.route?.let { currentRoute ->
            routeToCheck.any { currentRoute.startsWith(it) }
        } ?: false
        isNavigationBarVisible = !isFullScreenImage
    }

    SnackbarControllerProvider { host ->
        NavigationSuiteScaffold(
            layoutType = layoutType,
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
        ) {
            Scaffold(
                snackbarHost = { SnackbarHost(hostState = host) }
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
                        enterTransition = NavTransitions.slideUpIn(),
                        exitTransition = NavTransitions.slideDownOut(),
                        popEnterTransition = NavTransitions.slideDownIn(),
                        popExitTransition = NavTransitions.slideUpOut()
                    ) {
                        ChatHistoryScreen(navController = navController, chatViewModel = chatViewModel)
                    }
                    composable(Screen.ImagineScreen.route) {
                        SupportingPaneLayout(imageGenViewModel = imageGenViewModel, navController = navController, supportingPaneNavigator = supportingPaneNavigator)
                    }
                    composable(
                        route = Screen.ImageGenerationLoadingScreen.route,
                        enterTransition = NavTransitions.slideLeftIn(),
                        exitTransition = NavTransitions.slideRightOut(),
                        popEnterTransition = NavTransitions.slideLeftIn(),
                        popExitTransition = NavTransitions.slideRightOut()
                    ) {
                        ImageGenerationLoadingScreen(navController = navController, imageGenViewModel = imageGenViewModel, supportingPaneNavigator = supportingPaneNavigator)
                    }
                    composable(
                        route = Screen.FullScreenImage.route,
                        enterTransition = NavTransitions.slideLeftIn(),
                        exitTransition = NavTransitions.slideRightOut(),
                        popEnterTransition = NavTransitions.slideRightIn(),
                        popExitTransition = NavTransitions.slideLeftOut()
                    ) {
                        FullScreenImage(imageGenViewModel = imageGenViewModel, navController = navController, supportingPaneNavigator = supportingPaneNavigator)
                    }
                    composable(
                        route = Screen.GeneratedImagesScreen.route,
                        enterTransition = NavTransitions.slideLeftIn(),
                        exitTransition = NavTransitions.slideRightOut(),
                        popEnterTransition = NavTransitions.slideRightIn(),
                        popExitTransition = NavTransitions.slideLeftOut()
                    ) {
                        GeneratedImagesScreen(imageGenViewModel = imageGenViewModel, navController = navController, supportingPaneNavigator = supportingPaneNavigator)
                    }
                }
            }
        }
    }
}


