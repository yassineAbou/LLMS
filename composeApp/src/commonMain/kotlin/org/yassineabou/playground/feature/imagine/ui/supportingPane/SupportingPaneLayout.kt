package org.yassineabou.playground.feature.imagine.ui.supportingPane

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
import org.yassineabou.playground.feature.imagine.ui.FullScreenImage
import org.yassineabou.playground.feature.imagine.ui.GeneratedImagesScreen
import org.yassineabou.playground.feature.imagine.ui.ImageGenViewModel
import org.yassineabou.playground.feature.imagine.ui.ImageGenerationLoadingScreen
import org.yassineabou.playground.feature.imagine.ui.ImagineScreen
import org.yassineabou.playground.feature.imagine.ui.util.rememberIsLargeScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SupportingPaneLayout(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel,
    supportingPaneNavigator: SupportingPaneNavigator
) {
    val navigator = rememberSupportingPaneScaffoldNavigator()

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

    AnimatedPane(modifier = modifier.safeContentPadding()) {
        when {
            isLargeScreen || currentScreen is SupportingPaneScreen.GeneratedImages -> {
                ImagineScreen(
                    navController = navController,
                    imageGenViewModel = imageGenViewModel,
                    supportingPaneNavigator = supportingPaneNavigator,
                    shouldShowSupportingPaneButton = shouldShowSupportingPaneButton
                )
            }
            isLargeScreen && currentScreen is SupportingPaneScreen.ImageCreationTimer  -> {
                ImageGenerationLoadingScreen(
                    navController = navController,
                    imageGenViewModel = imageGenViewModel,
                    supportingPaneNavigator = supportingPaneNavigator
                )
            }
            isLargeScreen && currentScreen is SupportingPaneScreen.FullScreenImage  -> {
                FullScreenImage(
                    navController = navController,
                    imageGenViewModel = imageGenViewModel,
                    supportingPaneNavigator = supportingPaneNavigator
                )
            }
            else -> {
                when (currentScreen) {
                    SupportingPaneScreen.ImageCreationTimer -> {
                        ImageGenerationLoadingScreen(
                            navController = navController,
                            imageGenViewModel = imageGenViewModel,
                            supportingPaneNavigator = supportingPaneNavigator
                        )
                    }
                    SupportingPaneScreen.FullScreenImage -> {
                        FullScreenImage(
                            navController = navController,
                            imageGenViewModel = imageGenViewModel,
                            supportingPaneNavigator = supportingPaneNavigator
                        )
                    }
                    else -> Unit
                }
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
                slideInHorizontally { width -> width } + fadeIn() togetherWith
                        slideOutHorizontally { width -> -width } + fadeOut()
            } else {
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
            is SupportingPaneScreen.ImageCreationTimer -> {
                ImageGenerationLoadingScreen(
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


