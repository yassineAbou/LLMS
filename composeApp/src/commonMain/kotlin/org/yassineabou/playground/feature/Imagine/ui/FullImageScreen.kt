package org.yassineabou.playground.feature.Imagine.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.yassineabou.playground.app.core.navigation.Screen
import org.yassineabou.playground.app.core.sharedViews.BottomSheetContent
import org.yassineabou.playground.app.core.sharedViews.FullScreenBackIcon
import org.yassineabou.playground.app.core.sharedViews.PyramidText
import org.yassineabou.playground.app.core.sharedViews.SnackbarController
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.app.core.util.PlatformConfig
import org.yassineabou.playground.app.core.util.isDesktop
import org.yassineabou.playground.app.core.util.isWasm
import org.yassineabou.playground.feature.Imagine.model.UrlExample
import org.yassineabou.playground.feature.Imagine.ui.supportingPane.SupportingPaneNavigator
import org.yassineabou.playground.feature.Imagine.ui.supportingPane.SupportingPaneScreen
import org.yassineabou.playground.feature.Imagine.ui.supportingPane.rememberIsLargeScreen


@Composable
fun FullScreenImage(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel,
    supportingPaneNavigator: SupportingPaneNavigator? = null
) {
    val listGeneratedPhotos by imageGenViewModel.listGeneratedPhotos.collectAsStateWithLifecycle()
    val currentImageIndex by imageGenViewModel.currentImageIndex.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { listGeneratedPhotos.size }, initialPage = currentImageIndex)
    var showInfoBottomSheet by remember { mutableStateOf(false) }
    val isLargeScreen = rememberIsLargeScreen()

    LaunchedEffect(currentImageIndex) {
        if (currentImageIndex != pagerState.currentPage) {
            pagerState.animateScrollToPage(currentImageIndex)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        FullScreenBackIcon(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.Start),
            onBackPress = {
                if (isLargeScreen) {
                    supportingPaneNavigator?.navigate(SupportingPaneScreen.GeneratedImages)
                } else {
                    navController.navigate(Screen.GeneratedImagesScreen.route) {
                        popUpTo(Screen.ImagineScreen.route)
                    }
                }
            }
        )

        if (listGeneratedPhotos.isNotEmpty()) {
            ImagePager(
                pagerState = pagerState,
                listGenerated = listGeneratedPhotos,
                updateCurrentImageIndex = { index ->
                    imageGenViewModel.updateCurrentImageIndex(index)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        } else {
            // Handle empty state
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("No images available")
            }
        }

        FullScreenBottomBar(
            onInfoClicked = { showInfoBottomSheet = true },
            onDelete = {
                imageGenViewModel.deletePhoto(pagerState.currentPage)
                val newPage = if (pagerState.currentPage > 0) pagerState.currentPage - 1 else 0
                coroutineScope.launch {
                    pagerState.animateScrollToPage(newPage)
                }
            }
        )

        if (showInfoBottomSheet) {
            InfoBottomSheet(
                description = pagerState.currentPage.let { listGeneratedPhotos[it].description },
                onDismissRequest = { showInfoBottomSheet = false }
            )
        }
    }
}

@Composable
private fun ImagePager(
    pagerState: PagerState,
    listGenerated: List<UrlExample>,
    updateCurrentImageIndex: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) { page ->
            val image = listGenerated.getOrNull(page)
            if (image != null) {
                ImageReview(
                    url = image.url,
                    description = image.description,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        if (PlatformConfig.isDesktop() or PlatformConfig.isWasm()) {
            NavigationArrows(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                onPreviousPage = {
                    coroutineScope.launch {
                        if (pagerState.currentPage > 0) {
                            updateCurrentImageIndex(pagerState.currentPage - 1)
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                },
                onNextPage = {
                    coroutineScope.launch {
                        if (pagerState.currentPage < listGenerated.lastIndex) {
                            updateCurrentImageIndex(pagerState.currentPage + 1)
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
            )
        }
    }
}

@Composable
private fun ImageReview(
    modifier: Modifier = Modifier,
    url: String,
    description: String
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = url,
            contentDescription = "Wallpaper",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Text(
            text = description,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorSchemeCustom.alwaysWhite,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 16.dp, end = 16.dp, top = 100.dp)
        )
    }
}


@Composable
private fun NavigationArrows(
    onPreviousPage: () -> Unit,
    onNextPage: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Previous Arrow Button
        IconButton(
            onClick = onPreviousPage,
            modifier = Modifier.size(48.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorSchemeCustom.alwaysWhite.copy(alpha = 0.3f), // Semi-transparent white
                        shape = CircleShape
                    )
                    .size(40.dp), // Adjust size as needed
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = "Left Arrow",
                    tint = MaterialTheme.colorSchemeCustom.alwaysWhite,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }

        // Next Arrow Button
        IconButton(
            onClick = onNextPage,
            modifier = Modifier.size(48.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorSchemeCustom.alwaysWhite.copy(alpha = 0.3f), // Semi-transparent white
                        shape = CircleShape
                    )
                    .size(40.dp), // Adjust size as needed
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "Right Arrow",
                    tint = MaterialTheme.colorSchemeCustom.alwaysWhite
                )
            }
        }
    }
}


@Composable
private fun FullScreenBottomBar(
    isInFullScreen: Boolean = true,
    onInfoClicked: () -> Unit,
    onDelete: () -> Unit
) {
    val snackbarController = SnackbarController.current
    Row(
        modifier = Modifier
             .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconWithLabel(
            icon = Icons.Filled.Download,
            text = "Download",
            onClick = { snackbarController.showMessage("Image saved to your gallery") }
        )
        if (isInFullScreen) {
            IconWithLabel(
                icon = Icons.Filled.Info,
                text = "Info",
                onClick = onInfoClicked
            )
        }
        IconWithLabel(
            icon = Icons.Filled.Share,
            text = "Share",
            onClick = { snackbarController.showMessage("Your image has been shared") }
        )
        IconWithLabel(
            icon = Icons.Filled.Delete,
            text = "Delete",
            onClick = onDelete
        )
    }
}


@Composable
private fun IconWithLabel(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text
            )
        }
        Text(text = text, style = MaterialTheme.typography.labelMedium)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InfoBottomSheet(
    description: String,
    onDismissRequest: () -> Unit
) {
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        BottomSheetContent(
            body = {
                PyramidText(
                    text = description
                )
            }
        )
    }
}





