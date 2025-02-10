package org.yassineabou.playground.feature.Imagine.supportingPane

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldScope
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.yassineabou.playground.feature.Imagine.ui.FullScreenImage
import org.yassineabou.playground.feature.Imagine.ui.GeneratedImagesScreen
import org.yassineabou.playground.feature.Imagine.ui.ImageGenViewModel
import org.yassineabou.playground.feature.Imagine.ui.ImageProcessingScreen
import org.yassineabou.playground.feature.Imagine.ui.ImagineScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SupportingPaneLayout(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel
) {
    val navigator = rememberSupportingPaneScaffoldNavigator()
    val supportingPaneNavigator = rememberSupportingPaneNavigator()

    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = {
            MainPane(
                navController = navController,
                imageGenViewModel = imageGenViewModel,
                supportingPaneNavigator = supportingPaneNavigator,
                shouldShowSupportingPaneButton = navigator.scaffoldValue[SupportingPaneScaffoldRole.Supporting] == PaneAdaptedValue.Hidden,
            )
        },
        supportingPane = {
            SupportingPane(
                navController = navController,
                supportingPaneNavigator = supportingPaneNavigator,
                imageGenViewModel = imageGenViewModel
            )
        }
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ThreePaneScaffoldScope.MainPane(
    navController: NavController,
    supportingPaneNavigator: SupportingPaneNavigator,
    imageGenViewModel: ImageGenViewModel,
    shouldShowSupportingPaneButton: Boolean,
    modifier: Modifier = Modifier,
) {
    val isLargeScreen = rememberIsLargeScreen()

    val currentScreen = supportingPaneNavigator.currentScreen

    // Determine which screen to show
    val screenToShow = determineScreenToShow(isLargeScreen, currentScreen)


    AnimatedPane(modifier = modifier.safeContentPadding()) {
        when (screenToShow) {
            "ImagineContent" -> {
                ImagineScreen(
                    navController = navController,
                    imageGenViewModel = imageGenViewModel,
                    supportingPaneNavigator = supportingPaneNavigator,
                    shouldShowSupportingPaneButton = shouldShowSupportingPaneButton
                )
            }

            "ImageProcessingScreen" -> {
                ImageProcessingScreen(
                    navController = navController,
                    imageGenViewModel = imageGenViewModel,
                    supportingPaneNavigator = supportingPaneNavigator
                )
            }

            "FullScreenImage" -> {
                FullScreenImage(
                    navController = navController,
                    imageGenViewModel = imageGenViewModel,
                    supportingPaneNavigator = supportingPaneNavigator
                )
            }
        }
    }
}

@Composable
fun SupportingPane(
    navController: NavController,
    supportingPaneNavigator: SupportingPaneNavigator,
    imageGenViewModel: ImageGenViewModel,
    modifier: Modifier = Modifier
) {
    val currentScreen = supportingPaneNavigator.currentScreen
    val isForward = supportingPaneNavigator.isForward

    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            if (isForward) {
                // Forward animation: slide in from the right, slide out to the left
                slideInHorizontally { width -> width } + fadeIn() togetherWith
                        slideOutHorizontally { width -> -width } + fadeOut()
            } else {
                // Backward animation: slide in from the left, slide out to the right
                slideInHorizontally { width -> -width } + fadeIn() togetherWith
                        slideOutHorizontally { width -> width } + fadeOut()
            }.using(SizeTransform(clip = false))
        },
        modifier = modifier.safeContentPadding()
    ) { targetScreen ->
        when (targetScreen) {
            is SupportingPaneScreen.GeneratedImages -> {
                GeneratedImagesScreen(
                    supportingPaneNavigator = supportingPaneNavigator,
                    imageGenViewModel = imageGenViewModel,
                    navController = navController
                )
            }

            is SupportingPaneScreen.ImageProcessing -> {
                ImageProcessingScreen(
                    supportingPaneNavigator = supportingPaneNavigator,
                    imageGenViewModel = imageGenViewModel,
                    navController = navController
                )
            }

            is SupportingPaneScreen.FullScreenImage -> {
                FullScreenImage(
                    supportingPaneNavigator = supportingPaneNavigator,
                    imageGenViewModel = imageGenViewModel,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun determineScreenToShow(
    isLargeScreen: Boolean,
    currentScreen: SupportingPaneScreen
): String {
    return when {
        isLargeScreen || currentScreen is SupportingPaneScreen.GeneratedImages -> "ImagineContent"
        currentScreen is SupportingPaneScreen.ImageProcessing -> "ImageProcessingScreen"
        currentScreen is SupportingPaneScreen.FullScreenImage -> "FullScreenImage"
        else -> "ImagineContent"
    }
}

