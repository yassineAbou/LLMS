@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)


package org.yassineabou.playground.feature.imagine.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil3.compose.AsyncImage
import com.dragselectcompose.core.DragSelectState
import com.dragselectcompose.core.rememberDragSelectState
import com.dragselectcompose.grid.LazyDragSelectVerticalGrid
import com.dragselectcompose.grid.indicator.IndicatorIconDefaults
import com.dragselectcompose.grid.indicator.SelectedIcon
import com.dragselectcompose.grid.indicator.UnselectedIcon
import org.yassineabou.playground.app.core.navigation.Screen
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.app.core.sharedViews.FullScreenBackIcon
import org.yassineabou.playground.app.core.util.PaneOrScreenNavigator
import org.yassineabou.playground.feature.imagine.model.UrlExample
import org.yassineabou.playground.feature.imagine.ui.supportingPane.SupportingPaneNavigator
import org.yassineabou.playground.feature.imagine.ui.supportingPane.SupportingPaneScreen
import org.yassineabou.playground.feature.imagine.ui.view.NoContentMessage
import org.yassineabou.playground.feature.imagine.ui.view.ImageSelectionControls

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GeneratedImagesScreen(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel,
    supportingPaneNavigator: SupportingPaneNavigator? = null,
    dragSelectState: DragSelectState<UrlExample> = rememberDragSelectState(compareSelector = { it.id }),
) {
    val listGeneratedPhotos by imageGenViewModel.listGeneratedPhotos.collectAsState()
    val selectedPhotos = dragSelectState.selected
    val inSelectionMode = dragSelectState.inSelectionMode
    val selectedPhotoCount = dragSelectState.selected.size
    val windowSizeClass = calculateWindowSizeClass()
    val columnCount = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 2
        WindowWidthSizeClass.Medium -> 3
        WindowWidthSizeClass.Expanded -> 2
        else -> 2
    }
    val isLargeScreen = windowSizeClass.widthSizeClass > WindowWidthSizeClass.Medium
    var screenWidth by remember { mutableStateOf(Dp.Unspecified) }
    // Get current navigation destination
    val currentDestination by navController.currentBackStackEntryAsState()
    val isCurrentDestination = remember(currentDestination) {
        currentDestination?.destination?.route == Screen.GeneratedImagesScreen.route
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val currentWidth = maxWidth
        LaunchedEffect(currentWidth) {
            screenWidth = currentWidth
            if (screenWidth > 840.dp && isCurrentDestination) {
                navController.navigate(Screen.ImagineScreen.route)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            AnimatedVisibility(
                visible = !inSelectionMode,
            ) {
                GeneratedImagesTopBar(
                    isLargeScreen = isLargeScreen,
                    modifier = Modifier.padding(vertical = 4.dp),
                    onBackPress = { navController.navigate(Screen.ImagineScreen.route) }
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
                        imageGenViewModel.deleteSelectedPhotos(selectedPhotos)
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
                        navController.navigate(Screen.ImagineScreen.route)
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
                    items { image ->
                        val isSelected = image in selectedPhotos
                        val selectedModifier =
                            if (isSelected) Modifier.background(MaterialTheme.colorSchemeCustom.alwaysGray)
                                .padding(4.dp) else Modifier
                        SelectableItem(
                            modifier = selectedModifier,
                            item = image,
                            selectedIcon = {
                                SelectedIcon(
                                    options = IndicatorIconDefaults.selectedIconOptions(
                                        tint = MaterialTheme.colorSchemeCustom.alwaysBlue,
                                        backgroundColor = MaterialTheme.colorSchemeCustom.alwaysWhite
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
                                    val itemIndex = listGeneratedPhotos.indexOf(image)
                                    if (itemIndex != -1) {
                                        // Save the index in the ViewModel
                                        imageGenViewModel.updateCurrentImageIndex(itemIndex)
                                        PaneOrScreenNavigator.navigateTo(
                                            supportingPaneNavigator = supportingPaneNavigator,
                                            navController = navController,
                                            isLargeScreen = isLargeScreen,
                                            paneDestination = SupportingPaneScreen.FullScreenImage,
                                            screenRoute = Screen.FullScreenImage.route
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
        model = image.url,
        contentScale = ContentScale.FillBounds,
        contentDescription = null,
        modifier = Modifier
            .size(220.dp)
            .then(
                if (!isInSelectionMode) Modifier.clickable { onClick() } else Modifier
            )
    )
}


