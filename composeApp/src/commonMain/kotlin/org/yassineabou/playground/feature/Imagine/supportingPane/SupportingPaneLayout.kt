package org.yassineabou.playground.feature.Imagine.supportingPane

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
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.feature.Imagine.ui.FullScreenImage
import org.yassineabou.playground.feature.Imagine.ui.GeneratedImagesScreen
import org.yassineabou.playground.feature.Imagine.ui.ImageGenViewModel
import org.yassineabou.playground.feature.Imagine.ui.ImageProcessingScreen
import org.yassineabou.playground.feature.Imagine.ui.ImagineScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SupportingPaneLayout(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel = koinViewModel()
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
                onNavigateToSupportingPane = { navigator.navigateTo(SupportingPaneScaffoldRole.Supporting) }
            )
        },
        supportingPane = {
            SupportingPane(
                navController = navController,
                imageGenViewModel = imageGenViewModel,
                supportingPaneNavigator = supportingPaneNavigator
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
    onNavigateToSupportingPane: () -> Unit,
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
                    showGeneratedImagesButton = shouldShowSupportingPaneButton,
                    onNavigateToSupportingPane = onNavigateToSupportingPane
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
                    startIndex = 0, // Default start index
                    imageGenViewModel = imageGenViewModel,
                    supportingPaneNavigator = supportingPaneNavigator
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ThreePaneScaffoldScope.SupportingPane(
    navController: NavController,
    supportingPaneNavigator: SupportingPaneNavigator,
    imageGenViewModel: ImageGenViewModel,
    modifier: Modifier = Modifier
) {
    AnimatedPane(modifier = modifier.safeContentPadding()) {
        when (val currentScreen = supportingPaneNavigator.currentScreen) {
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
                    startIndex = currentScreen.index,
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

