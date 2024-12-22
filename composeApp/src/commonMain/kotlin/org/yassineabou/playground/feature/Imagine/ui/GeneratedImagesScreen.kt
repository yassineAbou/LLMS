@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)


package org.yassineabou.playground.feature.Imagine.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dragselectcompose.core.DragSelectState
import com.dragselectcompose.core.rememberDragSelectState
import com.dragselectcompose.grid.LazyDragSelectVerticalGrid
import com.dragselectcompose.grid.indicator.IndicatorIconDefaults
import com.dragselectcompose.grid.indicator.SelectedIcon
import com.dragselectcompose.grid.indicator.UnselectedIcon
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.app.ui.navigation.Screen
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom
import org.yassineabou.playground.app.ui.view.FullScreenBackIcon
import org.yassineabou.playground.feature.Imagine.model.UrlExample
import org.yassineabou.playground.feature.Imagine.view.ImageSelectionControls

@Composable
fun GeneratedImagesScreen(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel = koinViewModel(),
    dragSelectState: DragSelectState<UrlExample> = rememberDragSelectState(compareSelector = { it.id })
) {
    val listGeneratedPhotos by imageGenViewModel.listGeneratedPhotos.collectAsState()
    val selectedPhotos = dragSelectState.selected
    val inSelectionMode = dragSelectState.inSelectionMode
    val selectedPhotoCount = dragSelectState.selected.size
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        val windowSizeClass = calculateWindowSizeClass()
        val columnCount = when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 2
            WindowWidthSizeClass.Medium -> 3
            WindowWidthSizeClass.Expanded -> 5
            else -> 2
        }
        AnimatedVisibility(
            visible = !inSelectionMode,
        ) {
            GeneratedImagesTopBar(
                modifier = Modifier.padding(vertical = 4.dp),
                onBackPress = { navController.popBackStack() }
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
                onDeleteClick = { /* Handle delete */ },
                onSelectAllClick = { dragSelectState.updateSelected(listGeneratedPhotos) }
            )
        }
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
                val selectedModifier = if (isSelected) Modifier.background(MaterialTheme.colorSchemeCustom.alwaysGray).padding(4.dp) else Modifier
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
                                  navController.navigate("${Screen.FullScreenImage.route}/${image.id}")
                          },
                      )
                }
            }
        }
    }
}

@Composable
private fun GeneratedImagesTopBar(
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit
) {
    Row(
        modifier = modifier,
    ) {
        FullScreenBackIcon(
            onBackPress = onBackPress
        )
        Text(
            text = "Generated Images",
            style = MaterialTheme.typography.titleLarge,
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
    KamelImage(
        resource = { asyncPainterResource(data = image.url)},
        contentScale = ContentScale.FillBounds,
        contentDescription = null,
        modifier = Modifier
            .size(220.dp)
            .then(
                if (!isInSelectionMode) Modifier.clickable { onClick() } else Modifier
            )
    )
}


