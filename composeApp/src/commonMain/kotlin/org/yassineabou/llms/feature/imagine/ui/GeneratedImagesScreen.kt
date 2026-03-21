
package org.yassineabou.llms.feature.imagine.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import androidx.window.core.layout.WindowSizeClass
import com.dragselectcompose.core.DragSelectState
import com.dragselectcompose.core.rememberDragSelectState
import com.dragselectcompose.grid.LazyDragSelectVerticalGrid
import com.dragselectcompose.grid.indicator.IndicatorIconDefaults
import com.dragselectcompose.grid.indicator.SelectedIcon
import com.dragselectcompose.grid.indicator.UnselectedIcon
import com.github.panpf.sketch.AsyncImage
import org.yassineabou.llms.app.core.navigation.FullScreenImageRoute
import org.yassineabou.llms.app.core.navigation.GeneratedImagesRoute
import org.yassineabou.llms.app.core.navigation.ImagineRoute
import org.yassineabou.llms.app.core.navigation.NavigateToImagineOnScreenExpansion
import org.yassineabou.llms.app.core.navigation.Navigator
import org.yassineabou.llms.app.core.sharedViews.FullScreenBackIcon
import org.yassineabou.llms.app.core.theme.colorSchemeCustom
import org.yassineabou.llms.feature.imagine.data.model.UrlExample
import org.yassineabou.llms.feature.imagine.ui.supportingPane.PaneOrScreenNavigator
import org.yassineabou.llms.feature.imagine.ui.supportingPane.SupportingPaneNavigator
import org.yassineabou.llms.feature.imagine.ui.supportingPane.SupportingPaneScreen
import org.yassineabou.llms.feature.imagine.ui.util.DefaultNavigationInfo
import org.yassineabou.llms.feature.imagine.ui.view.ImageSelectionControls
import org.yassineabou.llms.feature.imagine.ui.view.NoContentMessage

@Composable
fun GeneratedImagesScreen(
    navigator: Navigator,
    imagineViewModel: ImagineViewModel,
    supportingPaneNavigator: SupportingPaneNavigator,
    dragSelectState: DragSelectState<UrlExample> = rememberDragSelectState(compareSelector = { it.id }),
) {
    val listGeneratedPhotos by imagineViewModel.listGeneratedImages.collectAsState()
    val selectedPhotos = dragSelectState.selected
    val inSelectionMode = dragSelectState.inSelectionMode
    val selectedPhotoCount = dragSelectState.selected.size
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val columnCount = when {
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> 2 // ≥840 dp
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> 3  // ≥600 dp
        else -> 2 // <600 dp (COMPACT)
    }


    val isLargeScreen = windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)

    val backState = rememberNavigationEventState(
        currentInfo = DefaultNavigationInfo
    )

    NavigationBackHandler(
        state = backState,
        onBackCompleted = {
            navigator.navigate(ImagineRoute)
            supportingPaneNavigator.navigate(SupportingPaneScreen.GeneratedImages)
        }
    )

    NavigateToImagineOnScreenExpansion(
        navigator = navigator, // Nav3: Navigator instead of NavController
        currentRoute = GeneratedImagesRoute, // Nav3: NavKey instead of string
        onNavigate = {
            supportingPaneNavigator.navigate(SupportingPaneScreen.GeneratedImages)
            navigator.navigate(ImagineRoute) // Nav3: NavKey
        }
    )

    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .padding(4.dp)
    ) {
        AnimatedVisibility(
            visible = !inSelectionMode,
        ) {
            GeneratedImagesTopBar(
                isLargeScreen = isLargeScreen,
                modifier = Modifier.padding(vertical = 4.dp),
                onBackPress = {
                    navigator.navigate(ImagineRoute)
                    supportingPaneNavigator.navigate(SupportingPaneScreen.GeneratedImages)
                }
            )
        }
        AnimatedVisibility(
            visible = inSelectionMode,
        ) {
            ImageSelectionControls(
                selectedPhotoCount = selectedPhotoCount,
                disableSelectionMode = { dragSelectState.disableSelectionMode() },
                onDownloadClick = { /* Handle download */ },
                onShareClick = { /* Handle share */ },
                onDeleteClick = {
                    imagineViewModel.deleteSelectedImages(selectedPhotos)
                    dragSelectState.disableSelectionMode()
                },
                onSelectAllClick = { dragSelectState.updateSelected(listGeneratedPhotos) }
            )
        }

        if (listGeneratedPhotos.isEmpty()) {
            NoContentMessage(
                modifier = Modifier.fillMaxSize(),
                title = "Let's Create Magic!",
                subtitle = "Your gallery is empty. Start generating stunning images now!",
                buttonText = "Generate Images",
                onButtonClick = {
                    navigator.navigate(ImagineRoute)
                }
            )
        } else {

            LazyDragSelectVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(count = columnCount),
                items = listGeneratedPhotos,
                state = dragSelectState,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                itemsIndexed { index, image ->
                    val isSelected = image in selectedPhotos
                    val selectedModifier =
                        if (isSelected) Modifier.background(LightGray)
                            .padding(4.dp) else Modifier
                    SelectableItem(
                        modifier = selectedModifier,
                        item = image,
                        selectedIcon = {
                            SelectedIcon(
                                options = IndicatorIconDefaults.selectedIconOptions(
                                    tint = MaterialTheme.colorSchemeCustom.alwaysBlue,
                                    backgroundColor = Color.White
                                ),
                                modifier = Modifier.align(Alignment.TopStart)
                            )
                        },
                        unselectedIcon = { UnselectedIcon(Modifier.align(Alignment.TopStart)) }
                    ) {
                        ImageItem(
                            image = image,
                            isInSelectionMode = inSelectionMode,
                            onClick = {
                                if (index != -1) {
                                    imagineViewModel.updateCurrentImageIndex(index)
                                    PaneOrScreenNavigator.navigateTo(
                                        supportingPaneNavigator = supportingPaneNavigator,
                                        navigator = navigator,
                                        isLargeScreen = isLargeScreen,
                                        paneDestination = SupportingPaneScreen.FullScreenImage,
                                        screenRoute = FullScreenImageRoute
                                    )
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GeneratedImagesTopBar(
    isLargeScreen: Boolean,
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isLargeScreen) {
            FullScreenBackIcon(
                onBackPress = onBackPress
            )
        }
        Text(
            text = "Generated Images",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 8.dp)
                .weight(1f)
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun ImageItem(
    image: UrlExample,
    isInSelectionMode: Boolean,
    onClick: () -> Unit
) {
    AsyncImage(
        uri = image.url,
        contentScale = ContentScale.FillBounds,
        contentDescription = null,
        modifier = Modifier
            .size(220.dp)
            .then(
                if (!isInSelectionMode) Modifier.clickable { onClick() } else Modifier
            )
    )
}


