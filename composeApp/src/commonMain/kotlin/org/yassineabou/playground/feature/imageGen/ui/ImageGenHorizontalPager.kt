package org.yassineabou.playground.feature.imageGen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dragselectcompose.core.DragSelectState
import com.dragselectcompose.core.rememberDragSelectState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.app.ui.navigation.imageGenTabRows
import org.yassineabou.playground.feature.imageGen.CreateImageScreen
import org.yassineabou.playground.feature.imageGen.model.Photo


@Composable
fun ImageGenHorizontalPager(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel = koinViewModel(),
    dragSelectState: DragSelectState<Photo> = rememberDragSelectState(compareSelector = { it.id })
) {
    val scope = rememberCoroutineScope()
    val listGeneratedPhotos by imageGenViewModel.listGeneratedPhotos.collectAsState()
    val pagerState = rememberPagerState(pageCount = { imageGenTabRows.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }
    val selectedPhotoCount = dragSelectState.selected.size
    val inSelectionMode = dragSelectState.inSelectionMode
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = inSelectionMode,
            ) {
                SelectionControls(
                    selectedPhotoCount = selectedPhotoCount,
                    disableSelectionMode = { dragSelectState.disableSelectionMode() },
                    onDownloadClick = { /* Handle download */ },
                    onShareClick = { /* Handle share */ },
                    onDeleteClick = { /* Handle delete */ },
                    onSelectAllClick = { dragSelectState.updateSelected(listGeneratedPhotos) }
                )
            }

            AnimatedVisibility(
                visible = !inSelectionMode,
            ) {
                ImageGenTabBar(
                    selectedTabIndex = selectedTabIndex,
                    scope = scope,
                    pagerState = pagerState
                )
            }

            ImageGenContent(
                pagerState = pagerState,
                navController = navController,
                dragSelectState = dragSelectState
            )
        }
    }
}

@Composable
private fun ImageGenTabBar(
    selectedTabIndex: State<Int>,
    scope: CoroutineScope,
    pagerState: PagerState
) {
    TabRow(
        selectedTabIndex = selectedTabIndex.value,
        modifier = Modifier.fillMaxWidth()
    ) {
        imageGenTabRows.forEachIndexed { index, currentTab ->
            Tab(
                selected = selectedTabIndex.value == index,
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(text = currentTab.title) },
                icon = {
                    Icon(
                        imageVector = if (selectedTabIndex.value == index)
                            currentTab.selectedIcon else currentTab.unselectedIcon,
                        contentDescription = "ImageGen Tab Icon"
                    )
                }
            )
        }
    }
}

@Composable
fun CloseButton(
    disableSelectionMode: () -> Unit,
    selectedPhotoCount: Int
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.secondary,
        shadowElevation = 4.dp,
        modifier = Modifier
            .padding(12.dp)
            .clickable(onClick = disableSelectionMode)
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Filled.Close,
                contentDescription = "Close"
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = if (selectedPhotoCount > 0) "$selectedPhotoCount" else "  ",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(end = 8.dp)
            )
        }

    }
}

@Composable
private fun SelectionControls(
    selectedPhotoCount: Int,
    disableSelectionMode: () -> Unit,
    onDownloadClick: () -> Unit,
    onShareClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSelectAllClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CloseButton(
            selectedPhotoCount = selectedPhotoCount,
            disableSelectionMode = disableSelectionMode
        )
        ActionButtonsRow(
            onDownloadClick = onDownloadClick,
            onShareClick = onShareClick,
            onDeleteClick = onDeleteClick,
            onSelectAllClick = onSelectAllClick
        )
    }
}


@Composable
private fun ActionButtonsRow(
    onDownloadClick: () -> Unit,
    onShareClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSelectAllClick: () -> Unit
) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = onDownloadClick) {
            Icon(imageVector = Icons.Filled.Download, contentDescription = "Download")
        }

        IconButton(onClick = onShareClick) {
            Icon(imageVector = Icons.Filled.Share, contentDescription = "Share")
        }

        IconButton(onClick = onDeleteClick) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
        }

        IconButton(onClick = onSelectAllClick) {
            Icon(imageVector = Icons.Filled.CopyAll, contentDescription = "SelectAll")
        }
    }
}



@Composable
private fun ImageGenContent(
    pagerState: PagerState,
    navController: NavController,
    dragSelectState: DragSelectState<Photo>
) {
    HorizontalPager(state = pagerState) { index ->
        when (index) {
            0 -> CreateImageScreen()
            1 -> {
                GridImagesScreen(
                    navController= navController,
                    dragSelectState = dragSelectState
                )
            }
        }
    }
}