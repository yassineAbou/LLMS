@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package org.yassineabou.llms.feature.imagine.ui.supportingPane

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.yassineabou.llms.app.core.navigation.Navigator
import org.yassineabou.llms.feature.imagine.ui.FullScreenImage
import org.yassineabou.llms.feature.imagine.ui.GeneratedImagesScreen
import org.yassineabou.llms.feature.imagine.ui.ImageGenerationLoadingScreen
import org.yassineabou.llms.feature.imagine.ui.ImagineScreen
import org.yassineabou.llms.feature.imagine.ui.ImagineViewModel
import org.yassineabou.llms.feature.imagine.ui.util.rememberIsLargeScreen

@Composable
fun SupportingPaneLayout(
    navigator: Navigator,
    imagineViewModel: ImagineViewModel,
    supportingPaneNavigator: SupportingPaneNavigator
) {
    val scaffoldNavigator = rememberSupportingPaneScaffoldNavigator()

    SupportingPaneScaffold(
        directive = scaffoldNavigator.scaffoldDirective,
        value = scaffoldNavigator.scaffoldValue,
        mainPane = {
            MainPane(
                navigator = navigator,
                supportingPaneNavigator = supportingPaneNavigator,
                imagineViewModel = imagineViewModel,
                shouldShowSupportingPaneButton = scaffoldNavigator.scaffoldValue[SupportingPaneScaffoldRole.Supporting] == PaneAdaptedValue.Hidden,
            )
        },
        supportingPane = {
            SupportingPane(
                navigator = navigator,
                supportingPaneNavigator = supportingPaneNavigator,
                imagineViewModel = imagineViewModel
            )
        }
    )
}

@Composable
fun MainPane(
    navigator: Navigator,
    supportingPaneNavigator: SupportingPaneNavigator,
    imagineViewModel: ImagineViewModel,
    shouldShowSupportingPaneButton: Boolean,
    modifier: Modifier = Modifier,
) {
    val isLargeScreen = rememberIsLargeScreen()
    val currentScreen = supportingPaneNavigator.currentScreen

    Box(modifier = modifier.safeContentPadding()) {
        when {
            isLargeScreen || currentScreen is SupportingPaneScreen.GeneratedImages -> {
                ImagineScreen(
                    navigator = navigator,
                    imagineViewModel = imagineViewModel,
                    supportingPaneNavigator = supportingPaneNavigator,
                    shouldShowSupportingPaneButton = shouldShowSupportingPaneButton
                )
            }
            isLargeScreen && currentScreen is SupportingPaneScreen.ImageGenerationLoading  -> {
                ImageGenerationLoadingScreen(
                    navigator = navigator,
                    imagineViewModel = imagineViewModel,
                    supportingPaneNavigator = supportingPaneNavigator
                )
            }
            isLargeScreen && currentScreen is SupportingPaneScreen.FullScreenImage  -> {
                FullScreenImage(
                    navigator = navigator,
                    imagineViewModel = imagineViewModel,
                    supportingPaneNavigator = supportingPaneNavigator
                )
            }
            else -> {
                when (currentScreen) {
                    SupportingPaneScreen.ImageGenerationLoading -> {
                        ImageGenerationLoadingScreen(
                            navigator = navigator,
                            imagineViewModel = imagineViewModel,
                            supportingPaneNavigator = supportingPaneNavigator
                        )
                    }
                    SupportingPaneScreen.FullScreenImage -> {
                        FullScreenImage(
                            navigator = navigator,
                            imagineViewModel = imagineViewModel,
                            supportingPaneNavigator = supportingPaneNavigator
                        )
                    }
                    else -> {}
                }
            }
        }

    }
}

@Composable
fun SupportingPane(
    navigator: Navigator,
    supportingPaneNavigator: SupportingPaneNavigator,
    imagineViewModel: ImagineViewModel,
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
                    imagineViewModel = imagineViewModel,
                    navigator = navigator
                )
            }
            is SupportingPaneScreen.ImageGenerationLoading -> {
                ImageGenerationLoadingScreen(
                    supportingPaneNavigator = supportingPaneNavigator,
                    imagineViewModel = imagineViewModel,
                    navigator = navigator
                )
            }
            is SupportingPaneScreen.FullScreenImage -> {
                FullScreenImage(
                    supportingPaneNavigator = supportingPaneNavigator,
                    imagineViewModel = imagineViewModel,
                    navigator = navigator
                )
            }
        }
    }
}