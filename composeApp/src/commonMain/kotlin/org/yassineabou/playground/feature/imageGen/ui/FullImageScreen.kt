package org.yassineabou.playground.feature.imageGen.ui

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
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.NavHostController
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.app.ui.navigation.Screen
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom
import org.yassineabou.playground.app.ui.util.PlatformConfig
import org.yassineabou.playground.app.ui.util.isDesktop
import org.yassineabou.playground.app.ui.view.BottomSheetContent
import org.yassineabou.playground.app.ui.view.PyramidText
import org.yassineabou.playground.app.ui.view.SnackbarController
import org.yassineabou.playground.feature.imageGen.model.UrlExample
import org.yassineabou.playground.feature.imageGen.view.FullScreenTopBar


@Composable
fun FullScreenImage(
    navController: NavHostController,
    startIndex: Int,
    imageGenViewModel: ImageGenViewModel = koinViewModel(),
    ) {
    val listGeneratedPhotos by imageGenViewModel.listGeneratedPhotos.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { listGeneratedPhotos.size }, initialPage = startIndex)
    var showInfoBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        FullScreenTopBar(
            modifier = Modifier
            .padding(4.dp)
            .align(Alignment.Start),
            onBackPress = {
                navController.navigate(Screen.ImageGen.route) {
                    popUpTo(Screen.ImageGen.route) {
                        inclusive = true
                    }
                }
            }
        )

        ImagePager(
            pagerState = pagerState,
            listGenerated = listGeneratedPhotos,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        FullScreenBottomBar(
            onInfoClicked = { showInfoBottomSheet = true },
            onDelete = {
                imageGenViewModel.deletePhoto(pagerState.currentPage)
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
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
            val image = listGenerated[page]
            ImageReview(
                url = image.url,
                modifier = Modifier.fillMaxSize()
            )
        }

        if (PlatformConfig.isDesktop()) {
            NavigationArrows(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 20.dp),
                onPreviousPage = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                },
                onNextPage = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
            )
        }
    }
}

@Composable
private fun ImageReview(modifier: Modifier = Modifier, url: String) {
    KamelImage(
        resource = { asyncPainterResource(data = url)},
        contentDescription = "Wallpaper",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
    )
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
        IconButton(
            onClick
            = onPreviousPage,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = "Left Arrow",
                tint = MaterialTheme.colorSchemeCustom.alwaysWhite
            )
        }

        IconButton(
            onClick = onNextPage,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "Right Arrow",
                tint = MaterialTheme.colorSchemeCustom.alwaysWhite
            )
        }
    }
}


@Composable
private fun FullScreenBottomBar(
    isInFullScreen: Boolean = true,
    onInfoClicked: () -> Unit,
    onDelete: () -> Unit
) {
    val controller = SnackbarController.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconWithLabel(
            icon = Icons.Filled.Download,
            text = "Download",
            onClick = { controller.showMessage("Image saved to your gallery") }
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
            onClick = { controller.showMessage("Your image has been shared") }
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





