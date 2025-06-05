package org.yassineabou.llms.feature.imagine.ui

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
import androidx.compose.material.icons.filled.Warning
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.github.panpf.sketch.AsyncImage
import kotlinx.coroutines.launch
import org.yassineabou.llms.app.core.data.remote.GenerationState
import org.yassineabou.llms.app.core.navigation.Screen
import org.yassineabou.llms.app.core.sharedViews.BottomSheetContent
import org.yassineabou.llms.app.core.sharedViews.FullScreenBackIcon
import org.yassineabou.llms.app.core.sharedViews.PyramidTextFormat
import org.yassineabou.llms.app.core.sharedViews.SnackbarController
import org.yassineabou.llms.app.core.util.PaneOrScreenNavigator
import org.yassineabou.llms.app.core.util.PlatformConfig
import org.yassineabou.llms.app.core.util.isDesktop
import org.yassineabou.llms.app.core.util.isWasm
import org.yassineabou.llms.feature.imagine.model.UrlExample
import org.yassineabou.llms.feature.imagine.ui.supportingPane.SupportingPaneNavigator
import org.yassineabou.llms.feature.imagine.ui.supportingPane.SupportingPaneScreen
import org.yassineabou.llms.feature.imagine.ui.util.NavigateToImagineOnScreenExpansion
import org.yassineabou.llms.feature.imagine.ui.util.rememberIsLargeScreen



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FullScreenImage(
    navController: NavController,
    imageGenViewModel: ImageGenViewModel,
    supportingPaneNavigator: SupportingPaneNavigator
) {
    val listGeneratedPhotos by imageGenViewModel.listGeneratedImages.collectAsStateWithLifecycle()
    val currentImageIndex by imageGenViewModel.currentImageIndex.collectAsStateWithLifecycle()
    val imageGenerationState by imageGenViewModel.imageGenerationState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    val snackbarController = SnackbarController.current
    val pagerState = rememberPagerState(
        pageCount = { listGeneratedPhotos.size },
        initialPage = currentImageIndex
    )
    var showInfoBottomSheet by remember { mutableStateOf(false) }
    val isLargeScreen = rememberIsLargeScreen()

    BackHandler {
        imageGenViewModel.resetImageGenerationState()
        PaneOrScreenNavigator.navigateTo(
            supportingPaneNavigator = supportingPaneNavigator,
            navController = navController,
            isLargeScreen = isLargeScreen,
            paneDestination = SupportingPaneScreen.GeneratedImages,
            screenRoute = Screen.GeneratedImagesScreen.route
        )
    }


    LaunchedEffect(currentImageIndex) {
        if (currentImageIndex != pagerState.currentPage) {
            pagerState.animateScrollToPage(currentImageIndex)
        }
    }

    LaunchedEffect(Unit) {
        imageGenViewModel.snackbarMessage.collect { message ->
            snackbarController.showMessage(message)
        }
    }

    NavigateToImagineOnScreenExpansion(
        navController = navController,
        targetRoute = Screen.FullScreenImage.route,
        onNavigate = {
            supportingPaneNavigator.navigate(SupportingPaneScreen.FullScreenImage)
            navController.navigate(Screen.ImagineScreen.route)
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        FullScreenBackIcon(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.Start),
            onBackPress = {
                imageGenViewModel.resetImageGenerationState()
                PaneOrScreenNavigator.navigateTo(
                    supportingPaneNavigator = supportingPaneNavigator,
                    navController = navController,
                    isLargeScreen = isLargeScreen,
                    paneDestination = SupportingPaneScreen.GeneratedImages,
                    screenRoute = Screen.GeneratedImagesScreen.route
                )
            }
        )

        when {
            imageGenerationState is GenerationState.Success && listGeneratedPhotos.isEmpty() -> {
                EmptyImagesPlaceholder(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
            }

            imageGenerationState is GenerationState.Failure -> {
                ImageGenerationError(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .padding(horizontal = 16.dp)
                )
            }
            else -> {
                ImagePager(
                    pagerState = pagerState,
                    listGenerated = listGeneratedPhotos,
                    imageGenerationState = imageGenerationState,
                    updateCurrentImageIndex = { index ->
                        imageGenViewModel.updateCurrentImageIndex(index)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
        }

        FullScreenBottomBar(
            onInfoClicked = { showInfoBottomSheet = true },
            onDownload =  { imageGenViewModel.downloadImage() },
            onDelete = {
                val currentId = listGeneratedPhotos[pagerState.currentPage].id
                imageGenViewModel.deleteImage(currentId)
                val newPage = if (pagerState.currentPage > 0) pagerState.currentPage - 1 else 0
                imageGenViewModel.updateCurrentImageIndex(newPage)
                coroutineScope.launch {
                    pagerState.animateScrollToPage(newPage)
                }
            }
        )

        if (showInfoBottomSheet  and listGeneratedPhotos.isNotEmpty()) {
            InfoBottomSheet(
                description = pagerState.currentPage.let { listGeneratedPhotos[it].prompt },
                onDismissRequest = { showInfoBottomSheet = false }
            )
        }
    }
}

@Composable
fun EmptyImagesPlaceholder(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No images available",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
fun ImageGenerationError(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Error",
                color = Color.Red,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "There was an error spinning up your chute. Please try again. If the problem persists, please contact support.",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun ImagePager(
    pagerState: PagerState,
    listGenerated: List<UrlExample>,
    imageGenerationState: GenerationState,
    updateCurrentImageIndex: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        updateCurrentImageIndex(pagerState.currentPage)
    }

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
                    description = image.prompt,
                    imageGenerationState = imageGenerationState,
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
    imageGenerationState: GenerationState,
    url: String,
    description: String
) {

    Box(modifier = modifier.fillMaxSize()) {
        if (imageGenerationState is GenerationState.Success) {
            AsyncImage(
                uri = url,
                contentDescription = description,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        if (imageGenerationState is GenerationState.Failure) {
            Icon(
                imageVector = Icons.Filled.Warning,
                contentDescription = "Error loading image",
                modifier = Modifier.align(Alignment.Center)
            )
        }
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
                        color = Color.White.copy(alpha = 0.3f), // Semi-transparent white
                        shape = CircleShape
                    )
                    .size(40.dp), // Adjust size as needed
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = "Left Arrow",
                    tint = Color.White,
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
                        color = Color.White.copy(alpha = 0.3f), // Semi-transparent white
                        shape = CircleShape
                    )
                    .size(40.dp), // Adjust size as needed
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "Right Arrow",
                    tint = Color.White
                )
            }
        }
    }
}


@Composable
private fun FullScreenBottomBar(
    onDownload: () -> Unit,
    onInfoClicked: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconWithLabel(
            icon = Icons.Filled.Download,
            text = "Download",
            onClick = onDownload
        )
        IconWithLabel(
            icon = Icons.Filled.Info,
            text = "Info",
            onClick = onInfoClicked
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
                PyramidTextFormat(
                    modifier = Modifier.padding(end = 16.dp),
                    text = description
                )
            }
        )
    }
}





