package org.yassineabou.playground.app.ui.navigation

sealed class Screen(val route: String) {
    data object ChatScreen : Screen("ChatScreen")
    data object ChatHistoryScreen : Screen("ChatHistoryScreen")
    data object ImagineScreen : Screen("ImagineScreen")
    data object GeneratedImagesScreen : Screen("GeneratedImagesScreen")
    data object Profile : Screen("Profile")
    data object FullScreenImage : Screen("FullScreenImage")
    data object ImageProcessingScreen : Screen("ImageProcessingScreen")
    data object SearchHistoryScreen : Screen("SearchHistoryScreen")
}