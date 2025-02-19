package org.yassineabou.playground.app.core.navigation

import androidx.compose.runtime.saveable.Saver
import org.yassineabou.playground.feature.imagine.ui.supportingPane.SupportingPaneScreen

sealed class Screen(val route: String) {
    data object ChatScreen : Screen("ChatScreen")
    data object ChatHistoryScreen : Screen("ChatHistoryScreen")
    data object ImagineScreen : Screen("ImagineScreen")
    data object GeneratedImagesScreen : Screen("GeneratedImagesScreen")
    data object Profile : Screen("Profile")
    data object FullScreenImage : Screen("FullScreenImage")
    data object ImageCreationTimerScreen : Screen("ImageCreationTimerScreen")

    companion object {

        fun fromRoute(route: String): Screen {
            return when (route) {
                ChatScreen.route -> ChatScreen
                ChatHistoryScreen.route -> ChatHistoryScreen
                ImagineScreen.route -> ImagineScreen
                GeneratedImagesScreen.route -> GeneratedImagesScreen
                Profile.route -> Profile
                FullScreenImage.route -> FullScreenImage
                ImageCreationTimerScreen.route -> ImageCreationTimerScreen
                else -> throw IllegalArgumentException("Unknown route: $route")
            }
        }

        fun fromSupportingPane(screen: SupportingPaneScreen): Screen {
            return when (screen) {
                SupportingPaneScreen.GeneratedImages -> GeneratedImagesScreen
                SupportingPaneScreen.ImageCreationTimer -> ImageCreationTimerScreen
                SupportingPaneScreen.FullScreenImage -> FullScreenImage
            }
        }


    }

    val ScreenSaver: Saver<Screen, String> = Saver(
        save = { screen -> screen.route }, // Convert Screen to String (route)
        restore = { route -> fromRoute(route) } // Convert String (route) back to Screen
    )
}