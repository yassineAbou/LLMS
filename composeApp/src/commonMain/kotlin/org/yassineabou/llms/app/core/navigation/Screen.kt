package org.yassineabou.llms.app.core.navigation

import androidx.compose.runtime.saveable.Saver


sealed class Screen(val route: String) {

    data object ChatScreen : Screen("ChatScreen")

    data object ChatHistoryScreen : Screen("ChatHistoryScreen")

    data object ImagineScreen : Screen("ImagineScreen")

    data object GeneratedImagesScreen : Screen("GeneratedImagesScreen")

    data object FullScreenImage : Screen("FullScreenImage")
    
    data object ImageGenerationLoadingScreen : Screen("ImageGenerationLoadingScreen")

    data object YouScreen : Screen("YouScreen")

    companion object {

        fun fromRoute(route: String): Screen {
            return when (route) {
                ChatScreen.route -> ChatScreen
                ChatHistoryScreen.route -> ChatHistoryScreen
                ImagineScreen.route -> ImagineScreen
                GeneratedImagesScreen.route -> GeneratedImagesScreen
                FullScreenImage.route -> FullScreenImage
                ImageGenerationLoadingScreen.route -> ImageGenerationLoadingScreen
                YouScreen.route -> YouScreen
                else -> throw IllegalArgumentException("Unknown route: $route")
            }
        }


    }

    val ScreenSaver: Saver<Screen, String> = Saver(
        save = { screen -> screen.route }, // Convert Screen to String (route)
        restore = { route -> fromRoute(route) } // Convert String (route) back to Screen
    )
}