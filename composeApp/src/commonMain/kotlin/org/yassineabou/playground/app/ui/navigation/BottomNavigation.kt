package org.yassineabou.playground.app.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dragselectcompose.core.rememberDragSelectState
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.app.ui.view.SnackbarControllerProvider
import org.yassineabou.playground.feature.Imagine.model.UrlExample
import org.yassineabou.playground.feature.Imagine.supportingPane.SupportingPaneLayout
import org.yassineabou.playground.feature.Imagine.ui.FullScreenImage
import org.yassineabou.playground.feature.Imagine.ui.GeneratedImagesScreen
import org.yassineabou.playground.feature.Imagine.ui.ImageProcessingScreen
import org.yassineabou.playground.feature.chat.ui.ChatScreen
import org.yassineabou.playground.feature.chat.ui.ChatViewModel
import org.yassineabou.playground.feature.chat.ui.history.ChatHistoryScreen
import org.yassineabou.playground.feature.chat.ui.history.SearchHistoryScreen
import org.yassineabou.playground.feature.profile.ui.ProfileContent


@Composable
fun BottomNavigation() {
    val navController = rememberNavController()
    var isBottomBarVisible by rememberSaveable { (mutableStateOf(true)) }
    var isFullScreenImage by rememberSaveable { (mutableStateOf(true)) }
    val dragSelectState = rememberDragSelectState<UrlExample>(compareSelector = { it.id })
    val isSelectionMode = dragSelectState.inSelectionMode
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val chatViewModel = koinViewModel<ChatViewModel>()

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
                NavHost(navController, startDestination = Screen.ChatScreen.route, Modifier.padding(innerPadding)) {
                    composable(Screen.ChatScreen.route) {
                        ChatScreen(navController = navController, chatViewModel = chatViewModel)
                    }
                    composable(
                        route = Screen.ChatHistoryScreen.route
                    ) {
                        ChatHistoryScreen(navController = navController, chatViewModel = chatViewModel)
                    }
                    composable(
                        route = Screen.SearchHistoryScreen.route
                    ) {
                        SearchHistoryScreen(navController = navController)
                    }
                    composable(Screen.ImagineScreen.route) {
                        SupportingPaneLayout(navController = navController)
                    }
                    composable(
                        route = Screen.ImageProcessingScreen.route,
                    ) {
                        ImageProcessingScreen(navController = navController)
                    }
                    composable(
                        route = "FullScreenImage/{startIndex}",
                        arguments = listOf(navArgument("startIndex") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val startIndex = backStackEntry.arguments?.getInt("startIndex") ?: 0
                        FullScreenImage(
                            navController = navController,
                            startIndex = startIndex
                        )
                    }
                    composable(
                        route = Screen.GeneratedImagesScreen.route
                    ) {
                        GeneratedImagesScreen(
                            navController = navController
                        )
                    }
                    composable(Screen.Profile.route) {
                        ProfileContent(navController = navController)
                    }
                }
            }
        )
    }
}

data class BottomBarItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)

@Composable
private fun BottomNavigationBar(
    listBottomBarItems: List<BottomBarItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        listBottomBarItems.forEach { item ->
            NavigationBarItem(
                label = {
                    Text(text = item.name)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name,
                    )
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }

}

val listBottomBarItems = listOf(
    BottomBarItem(
        name = "Chat",
        route = Screen.ChatScreen.route,
        icon = Icons.AutoMirrored.Filled.Chat,
    ),
    BottomBarItem(
        name = "Imagine",
        route = Screen.ImagineScreen.route,
        icon = Icons.Default.Image,
    ),
    BottomBarItem(
        name = "Profile",
        route = Screen.Profile.route,
        icon = Icons.Default.Person,
    ),
)

