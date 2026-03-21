
package org.yassineabou.llms.feature.imagine.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import org.yassineabou.llms.app.core.data.remote.ai.GenerationState
import org.yassineabou.llms.app.core.navigation.FullScreenImageRoute
import org.yassineabou.llms.app.core.navigation.GeneratedImagesRoute
import org.yassineabou.llms.app.core.navigation.ImageGenerationLoadingRoute
import org.yassineabou.llms.app.core.navigation.ImagineRoute
import org.yassineabou.llms.app.core.navigation.NavigateToImagineOnScreenExpansion
import org.yassineabou.llms.app.core.navigation.Navigator
import org.yassineabou.llms.app.core.sharedViews.LoadingContent
import org.yassineabou.llms.app.core.theme.colorSchemeCustom
import org.yassineabou.llms.feature.imagine.ui.supportingPane.PaneOrScreenNavigator
import org.yassineabou.llms.feature.imagine.ui.supportingPane.SupportingPaneNavigator
import org.yassineabou.llms.feature.imagine.ui.supportingPane.SupportingPaneScreen
import org.yassineabou.llms.feature.imagine.ui.util.DefaultNavigationInfo
import org.yassineabou.llms.feature.imagine.ui.util.rememberIsLargeScreen

@Composable
fun ImageGenerationLoadingScreen(
    navigator: Navigator,
    imagineViewModel: ImagineViewModel,
    supportingPaneNavigator: SupportingPaneNavigator,
    modifier: Modifier = Modifier
) {
    val imageGenerationState by imagineViewModel.imageGenerationState.collectAsStateWithLifecycle()
    val isLargeScreen = rememberIsLargeScreen()
    val backState = rememberNavigationEventState(
        currentInfo = DefaultNavigationInfo
    )

    NavigationBackHandler(
        state = backState,
        onBackCompleted = {
            imagineViewModel.cancelImageGeneration()
        }
    )


    when (imageGenerationState) {
        is GenerationState.Success,
        is GenerationState.Failure -> {
            PaneOrScreenNavigator.navigateTo(
                supportingPaneNavigator = supportingPaneNavigator,
                navigator = navigator,
                isLargeScreen = isLargeScreen,
                paneDestination = SupportingPaneScreen.FullScreenImage,
                screenRoute = FullScreenImageRoute
            )
        }

        is GenerationState.Cancelled -> {
            PaneOrScreenNavigator.navigateTo(
                supportingPaneNavigator = supportingPaneNavigator,
                navigator = navigator,
                isLargeScreen = isLargeScreen,
                paneDestination = SupportingPaneScreen.GeneratedImages,
                screenRoute = GeneratedImagesRoute
            )
        }

        else -> Unit
    }


    NavigateToImagineOnScreenExpansion(
        navigator = navigator,
        currentRoute = ImageGenerationLoadingRoute,
        onNavigate = {
            supportingPaneNavigator.navigate(SupportingPaneScreen.ImageGenerationLoading)
            navigator.navigate(ImagineRoute)
        }
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        LoadingContent(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)

        )

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorSchemeCustom.alwaysBlue,
                contentColor = Color.White
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
                .padding(bottom = 48.dp),
            onClick = {
                imagineViewModel.cancelImageGeneration()
            }
        ) {
            Text(
                text = "Cancel",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
            )
        }
    }
}