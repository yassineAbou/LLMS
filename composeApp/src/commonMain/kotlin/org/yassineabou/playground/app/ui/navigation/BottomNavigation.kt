package org.yassineabou.playground.app.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.yassineabou.playground.feature.imageGen.FullScreenImage
import org.yassineabou.playground.feature.imageGen.ImageGenHorizontalPager
import org.yassineabou.playground.feature.profile.ui.ProfileContent
import org.yassineabou.playground.feature.textGen.TextGenHorizontalPager


@Composable
fun BottomNavigation() {
    val navController = rememberNavController()
    var isBottomBarVisible by rememberSaveable { (mutableStateOf(true)) }
    var isFullScreenImage by rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(navBackStackEntry?.destination?.route) {
        this.launch {
            isFullScreenImage = when (navBackStackEntry?.destination?.route) {
                Screen.FullScreenImage.route -> true
                else -> false
            }
            isBottomBarVisible = when (navBackStackEntry?.destination?.route) {
                Screen.FullScreenImage.route -> false
                else -> true
            }
        }
    }

        Scaffold(
            topBar = {},
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
                NavHost(navController, startDestination = Screen.TextGen.route, Modifier.padding(innerPadding)) {
                    composable(Screen.TextGen.route) { TextGenHorizontalPager(navController) }
                    composable(Screen.ImageGen.route) { ImageGenHorizontalPager(navController) }
                    composable(Screen.Profile.route) { ProfileContent(navController = navController) }
                    composable(Screen.FullScreenImage.route) { FullScreenImage(navController) }
                }
            }
        )
}

data class BottomBarItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)

@Composable
fun BottomNavigationBar(
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
        name = "TextGen",
        route = Screen.TextGen.route,
        icon = Icons.Default.Description,
    ),
    BottomBarItem(
        name = "ImageGen",
        route = Screen.ImageGen.route,
        icon = Icons.Default.Image,
    ),
    BottomBarItem(
        name = "Profile",
        route = Screen.Profile.route,
        icon = Icons.Default.Person,
    ),
)

