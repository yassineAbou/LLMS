package org.yassineabou.playground.feature.Imagine.view

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldScope
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.yassineabou.playground.feature.Imagine.ui.FullScreenImage
import org.yassineabou.playground.feature.Imagine.ui.GeneratedImagesScreen
import org.yassineabou.playground.feature.Imagine.ui.ImageGenViewModel
import org.yassineabou.playground.feature.Imagine.ui.ImageProcessingScreen
import org.yassineabou.playground.feature.Imagine.ui.ImagineContent

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SupportingPaneLayout(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel
) {
    val navigator = rememberSupportingPaneScaffoldNavigator()
    val supportingPaneNavigator = rememberSupportingPaneNavigator()

    /*
    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }
     */

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
    supportingPaneNavigator: SupportingPaneNavigator, // Add this parameter
    imageGenViewModel: ImageGenViewModel,
    shouldShowSupportingPaneButton: Boolean,
    onNavigateToSupportingPane: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedPane(modifier = modifier.safeContentPadding()) {
        ImagineContent(
            navController = navController,
            imageGenViewModel = imageGenViewModel,
            supportingPaneNavigator = supportingPaneNavigator,
            showGeneratedImagesButton = shouldShowSupportingPaneButton,
            onNavigateToSupportingPane = onNavigateToSupportingPane
        )
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

sealed class SupportingPaneScreen {
    object GeneratedImages : SupportingPaneScreen()
    object ImageProcessing : SupportingPaneScreen()
    data class FullScreenImage(val index: Int) : SupportingPaneScreen()
}

class SupportingPaneNavigator(initialScreen: SupportingPaneScreen) {
    private val _backStack = mutableStateListOf(initialScreen)
    val currentScreen: SupportingPaneScreen
        get() = _backStack.last()

    fun navigate(screen: SupportingPaneScreen) {
        _backStack.add(screen)
    }

    fun popBackStack(): Boolean {
        if (_backStack.size > 1) {
            _backStack.removeAt(_backStack.size - 1)
            return true
        }
        return false
    }
}

@Composable
fun rememberSupportingPaneNavigator(initialScreen: SupportingPaneScreen = SupportingPaneScreen.GeneratedImages): SupportingPaneNavigator {
    return remember { SupportingPaneNavigator(initialScreen) }
}

