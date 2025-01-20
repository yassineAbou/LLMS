package org.yassineabou.playground.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dragselectcompose.core.rememberDragSelectState
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.app.di.appModule
import org.yassineabou.playground.app.ui.navigation.BottomNavigationBar
import org.yassineabou.playground.app.ui.navigation.Screen
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
import org.yassineabou.playground.feature.chat.ui.ChatScreen
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

    LaunchedEffect(navBackStackEntry?.destination?.route, isSelectionMode) {

        val routeToCheck = listOf(Screen.FullScreenImage.route, Screen.ImageProcessingScreen.route, Screen.ChatHistoryScreen.route, Screen.GeneratedImagesScreen.route, Screen.SearchHistoryScreen.route)

        isFullScreenImage = navBackStackEntry?.destination?.route?.let {currentRoute ->
            routeToCheck.any { currentRoute.startsWith(it) }
        } ?: false

        isBottomBarVisible =!isFullScreenImage and !isSelectionMode
    }


    SnackbarControllerProvider { host ->
        Scaffold(
            topBar = {},
            snackbarHost = { SnackbarHost(hostState = host) },
            bottomBar = {
                AnimatedVisibility(
                    visible = isBottomBarVisible,
                    enter = scaleIn(),
                    exit = scaleOut(),
                ) {
                    BottomNavigationBar(
                        listBottomBarItems = listBottomBarItems,
                        navController = navController,
                    )
                }
            },
            content = { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.ChatScreen.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screen.ChatScreen.route) {
                        ChatScreen(navController = navController, chatViewModel = chatViewModel)
                    }
                    composable(Screen.ChatHistoryScreen.route) {
                        ChatHistoryScreen(
                            navController = navController,
                            chatViewModel = chatViewModel
                        )
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
                        ImageProcessingScreen(navController = navController)
                    }
                    composable(
                        route = "FullScreenImage/{startIndex}",
                        arguments = listOf(navArgument("startIndex") { type = NavType.IntType }),
                        enterTransition = { slideInFromRight() },
                        exitTransition = { slideOutToLeft() },
                        popEnterTransition = { slideInFromLeft() },
                        popExitTransition = { slideOutToRight() }
                    ) { backStackEntry ->
                        val startIndex = backStackEntry.arguments?.getInt("startIndex") ?: 0
                        FullScreenImage(imageGenViewModel = imageGenViewModel, navController = navController, startIndex = startIndex)
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
        )
    }
}

