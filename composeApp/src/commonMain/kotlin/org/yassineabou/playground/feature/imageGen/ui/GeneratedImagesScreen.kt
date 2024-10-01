@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)


package org.yassineabou.playground.feature.imageGen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.MaterialTheme
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
import com.dragselectcompose.grid.LazyDragSelectVerticalGrid
import com.dragselectcompose.grid.indicator.SelectedIcon
import com.dragselectcompose.grid.indicator.UnselectedIcon
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.app.ui.navigation.Screen
import org.yassineabou.playground.feature.imageGen.model.Photo

@Composable
fun GridImagesScreen(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel = koinViewModel(),
    dragSelectState: DragSelectState<Photo>
) {
    val listGeneratedPhotos by imageGenViewModel.listGeneratedPhotos.collectAsState()
    val selectedPhotos = dragSelectState.selected
    val inSelectionMode = dragSelectState.inSelectionMode
    Box(
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
        LazyDragSelectVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(count = columnCount),
            items = listGeneratedPhotos,
            state = dragSelectState,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items { photo ->
                val isSelected = photo in selectedPhotos
                val selectedModifier = if (isSelected) Modifier.background(MaterialTheme.colorScheme.outline).padding(4.dp) else Modifier
                SelectableItem(
                    modifier = selectedModifier,
                    item = photo,
                    selectedIcon = { SelectedIcon(Modifier.align(Alignment.TopStart)) },
                    unselectedIcon = { UnselectedIcon(Modifier.align(Alignment.TopStart)) }
                ) {
                      PhotoItem(
                          photo = photo,
                          isInSelectionMode = inSelectionMode,
                          onClick = {
                                  navController.navigate("${Screen.FullScreenImage.route}/${photo.id}")
                          },
                      )
                }
            }
        }
    }
}

@Composable
fun PhotoItem(
    photo: Photo,
    isInSelectionMode: Boolean,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(photo.drawable),
        contentScale = ContentScale.FillBounds,
        contentDescription = null,
        modifier = Modifier
            .size(220.dp)
            .then(
                if (!isInSelectionMode) Modifier.clickable { onClick() } else Modifier
            )
    )
}


