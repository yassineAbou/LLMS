package org.yassineabou.llms.app

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.window.core.layout.WindowSizeClass
import com.sunildhiman90.kmauth.core.KMAuthConfig
import com.sunildhiman90.kmauth.core.KMAuthInitializer
import org.yassineabou.llms.app.core.di.kodeinViewModel
import org.yassineabou.llms.app.core.navigation.ChatHistoryRoute
import org.yassineabou.llms.app.core.navigation.ChatRoute
import org.yassineabou.llms.app.core.navigation.FullScreenImageRoute
import org.yassineabou.llms.app.core.navigation.GeneratedImagesRoute
import org.yassineabou.llms.app.core.navigation.ImageGenerationLoadingRoute
import org.yassineabou.llms.app.core.navigation.ImagineRoute
import org.yassineabou.llms.app.core.navigation.NavTransitions
import org.yassineabou.llms.app.core.navigation.Navigator
import org.yassineabou.llms.app.core.navigation.TOP_LEVEL_ROUTES
import org.yassineabou.llms.app.core.navigation.YouRoute
import org.yassineabou.llms.app.core.navigation.listNavigationBarItems
import org.yassineabou.llms.app.core.navigation.rememberNavigationState
import org.yassineabou.llms.app.core.navigation.toEntries
import org.yassineabou.llms.app.core.sharedViews.SnackbarControllerProvider
import org.yassineabou.llms.app.core.util.GoogleOAuthConfig
import org.yassineabou.llms.feature.chat.ui.ChatViewModel
import org.yassineabou.llms.feature.chat.ui.chat.ChatScreen
import org.yassineabou.llms.feature.chat.ui.history.ChatHistoryScreen
import org.yassineabou.llms.feature.imagine.ui.FullScreenImage
import org.yassineabou.llms.feature.imagine.ui.GeneratedImagesScreen
import org.yassineabou.llms.feature.imagine.ui.ImageGenerationLoadingScreen
import org.yassineabou.llms.feature.imagine.ui.ImagineViewModel
import org.yassineabou.llms.feature.imagine.ui.supportingPane.SupportingPaneLayout
import org.yassineabou.llms.feature.imagine.ui.supportingPane.rememberSupportingPaneNavigator
import org.yassineabou.llms.feature.you.ui.YouScreen
import org.yassineabou.llms.feature.you.ui.YouViewModel

@Composable
fun MainScreen() {

    KMAuthInitializer.initialize(KMAuthConfig.forGoogle(webClientId = GoogleOAuthConfig.CLIENT_ID))


    val navigationState = rememberNavigationState(
        startRoute = ChatRoute,
        topLevelRoutes = TOP_LEVEL_ROUTES
    )


    val navigator = remember { Navigator(navigationState) }

    val chatViewModel = kodeinViewModel<ChatViewModel>()
    val imagineViewModel = kodeinViewModel<ImagineViewModel>()
    val youViewModel = kodeinViewModel<YouViewModel>()
    val supportingPaneNavigator = rememberSupportingPaneNavigator()

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    val isNavigationBarVisible by remember {
        derivedStateOf { navigator.isNavigationBarVisible() }
    }

    val layoutType = when {
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
            NavigationSuiteType.NavigationRail
        }
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
            if (isNavigationBarVisible) NavigationSuiteType.NavigationBar else NavigationSuiteType.None
        }
        else -> {
            if (isNavigationBarVisible) NavigationSuiteType.NavigationBar else NavigationSuiteType.None
        }
    }


    val entryProvider: (NavKey) -> NavEntry<NavKey> = entryProvider {

        entry<ChatRoute> {
            ChatScreen(
                navigator = navigator,
                chatViewModel = chatViewModel
            )
        }

        entry<ChatHistoryRoute>(
            metadata = NavTransitions.slideVertical()
        ) {
            ChatHistoryScreen(
                navigator = navigator,
                chatViewModel = chatViewModel
            )
        }

        entry<ImagineRoute> {
            SupportingPaneLayout(
                navigator = navigator,
                imagineViewModel = imagineViewModel,
                supportingPaneNavigator = supportingPaneNavigator
            )
        }

        entry<ImageGenerationLoadingRoute>(
            metadata = NavTransitions.slideHorizontal()
        ) {
            ImageGenerationLoadingScreen(
                navigator = navigator,
                imagineViewModel = imagineViewModel,
                supportingPaneNavigator = supportingPaneNavigator
            )
        }

        entry<FullScreenImageRoute>(
            metadata = NavTransitions.slideHorizontal()
        ) {
            FullScreenImage(
                imagineViewModel = imagineViewModel,
                navigator = navigator,
                supportingPaneNavigator = supportingPaneNavigator
            )
        }

        entry<GeneratedImagesRoute>(
            metadata = NavTransitions.slideHorizontal()
        ) {
            GeneratedImagesScreen(
                imagineViewModel = imagineViewModel,
                navigator = navigator,
                supportingPaneNavigator = supportingPaneNavigator
            )
        }

        entry<YouRoute> {
            YouScreen(youViewModel = youViewModel)
        }
    }

    SnackbarControllerProvider { host ->
        NavigationSuiteScaffold(
            layoutType = layoutType,
            navigationSuiteItems = {
                listNavigationBarItems.forEach { item ->
                    item(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        },
                        label = { Text(text = item.name) },
                        selected = item.route == navigationState.topLevelRoute,
                        onClick = {
                            navigator.navigate(item.route)
                        }
                    )
                }
            },
        ) {
            Scaffold(
                snackbarHost = { SnackbarHost(hostState = host) }
            ) {
                NavDisplay(
                    entries = navigationState.toEntries(entryProvider),
                    onBack = { navigator.goBack() },
                    transitionSpec = {
                        EnterTransition.None togetherWith ExitTransition.None
                    },
                    popTransitionSpec = {
                        EnterTransition.None togetherWith ExitTransition.None
                    },
                    predictivePopTransitionSpec = {
                        EnterTransition.None togetherWith ExitTransition.None
                    }
                )
            }
        }
    }
}
