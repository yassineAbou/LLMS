package org.yassineabou.playground.app.ui.navigation

sealed class Screen(val route: String) {
    data object TextGen : Screen("TextGen")
    data object ImageGen: Screen("ImageGen")
    data object Profile : Screen("Profile")
    data object FullScreenImage : Screen("FullScreenImage")
}