package org.yassineabou.playground.feature.imagine.ui.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import kotlinx.coroutines.delay
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.app.core.util.draggableScrollModifier
import org.yassineabou.playground.app.core.sharedViews.BottomSheetContent
import org.yassineabou.playground.app.core.sharedViews.ModelTypeActionButtons
import org.yassineabou.playground.app.core.sharedViews.InfoIconButton
import org.yassineabou.playground.app.core.sharedViews.ModelInformation
import org.yassineabou.playground.app.core.sharedViews.ShimmerPlaceholder
import org.yassineabou.playground.feature.imagine.model.ImageGenModelList
import org.yassineabou.playground.feature.imagine.model.ImageModel
import org.yassineabou.playground.feature.imagine.model.UrlExample
import org.yassineabou.playground.feature.imagine.ui.ImageGenViewModel
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageModelsBottomSheet(
    imageGenViewModel: ImageGenViewModel,
    onDismissRequest: () -> Unit,
    onAuthenticated: () -> Unit
) {
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val tempSelectedImageModel by imageGenViewModel.tempSelectedImageModel.collectAsState()
    var isInfoIconClicked by remember { mutableStateOf(false) }
    var infoImageModel by remember { mutableStateOf(tempSelectedImageModel) }


    LaunchedEffect(Unit) {
        imageGenViewModel.setTempSelectedToSelected()
    }


    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        AnimatedVisibility(!isInfoIconClicked) {
            BottomSheetContent(
                title = {
                    ModelTypeActionButtons(
                        onDismissRequest = onDismissRequest,
                        onDone = {
                            onAuthenticated()
                            imageGenViewModel.confirmSelectedImageModel()
                        }
                    )
                },
                body = {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {
                            ImageModelType(
                                type = "Generalist",
                                imageModelsList = ImageGenModelList.generalist,
                                tempSelectedImageModel = tempSelectedImageModel,
                                onImageModelSelected = imageGenViewModel::selectTempImageModel,
                                onInfoClick = { imageModel ->
                                    isInfoIconClicked = true
                                    infoImageModel = imageModel
                                }
                            )
                        }
                        item {
                            ImageModelType(
                                type = "Realistic",
                                imageModelsList = ImageGenModelList.realistic,
                                tempSelectedImageModel = tempSelectedImageModel,
                                onImageModelSelected = imageGenViewModel::selectTempImageModel,
                                onInfoClick = { imageModel ->
                                    isInfoIconClicked = true
                                    infoImageModel = imageModel
                                }
                            )
                        }
                        item {
                            ImageModelType(
                                type = "Anime",
                                imageModelsList = ImageGenModelList.anime,
                                tempSelectedImageModel = tempSelectedImageModel,
                                onImageModelSelected = imageGenViewModel::selectTempImageModel,
                                onInfoClick = { imageModel ->
                                    isInfoIconClicked = true
                                    infoImageModel = imageModel
                                }
                            )
                        }
                        item {
                            ImageModelType(
                                type = "3D",
                                imageModelsList = ImageGenModelList.threeD,
                                tempSelectedImageModel = tempSelectedImageModel,
                                onImageModelSelected = imageGenViewModel::selectTempImageModel,
                                onInfoClick = { imageModel ->
                                    isInfoIconClicked = true
                                    infoImageModel = imageModel
                                }
                            )
                        }
                        item {
                            ImageModelType(
                                type = "Comic",
                                imageModelsList = ImageGenModelList.comic,
                                tempSelectedImageModel = tempSelectedImageModel,
                                onImageModelSelected = imageGenViewModel::selectTempImageModel,
                                onInfoClick = { imageModel ->
                                    isInfoIconClicked = true
                                    infoImageModel = imageModel
                                }
                            )
                        }
                        item {
                            ImageModelType(
                                type = "Furry",
                                imageModelsList = ImageGenModelList.furry,
                                tempSelectedImageModel = tempSelectedImageModel,
                                onImageModelSelected = imageGenViewModel::selectTempImageModel,
                                onInfoClick = { imageModel ->
                                    isInfoIconClicked = true
                                    infoImageModel = imageModel
                                }
                            )
                        }
                    }
                }
            )
        }
        AnimatedVisibility(isInfoIconClicked) {
            ModelInformation(
                imageModel = infoImageModel,
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .padding(horizontal = 16.dp),
                onDismissRequest = { isInfoIconClicked = false }
            )
        }
    }
}


@Composable
private fun ImageModelType(
    imageModelsList: List<ImageModel>,
    type: String,
    tempSelectedImageModel: ImageModel,
    onImageModelSelected: (ImageModel) -> Unit,
    onInfoClick: (ImageModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = type,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, bottom = 4.dp, end = 8.dp)
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 4.dp)
                .draggableScrollModifier(rememberLazyListState()), // Apply the reusable drag modifier for desktop and wasm ,
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(imageModelsList) { item ->
                imageModelItem(
                    tempSelectedImageModel = item,
                    isSelected = item == tempSelectedImageModel,
                    onSelected = { onImageModelSelected(item) },
                    onInfoClick = { onInfoClick(item) }
                )
            }

        }

    }
}

@Composable
private fun imageModelItem(
    tempSelectedImageModel: ImageModel,
    isSelected: Boolean,
    onSelected: () -> Unit,
    onInfoClick: () -> Unit
) {
    val backgroundColor =
        if (isSelected) MaterialTheme.colorSchemeCustom.alwaysBlue else Color.Transparent
    val textColor =
        if (isSelected) MaterialTheme.colorSchemeCustom.alwaysWhite else MaterialTheme.colorScheme.onBackground
    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            )
            .background(backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable(onClick = onSelected) // Open detailed view on click
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.CenterHorizontally)// Match the size of TextGenType
        ) {
            ImageCarousel(
                imageUrlExamples = tempSelectedImageModel.urlExamples
            )
            if (tempSelectedImageModel.isNsfw) {
                Text(
                    text = "NSFW",
                    color = MaterialTheme.colorSchemeCustom.alwaysWhite,
                    modifier = Modifier.background(Color.Red, shape = RoundedCornerShape(8.dp))
                        .align(Alignment.TopEnd)
                        .padding(4.dp),
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = tempSelectedImageModel.title,
                color = textColor,
                style = MaterialTheme.typography.bodyLarge,
            )
            InfoIconButton(
                onInfoClick = onInfoClick
            )
        }
    }
}


@Composable
fun ImageCarousel(
    imageUrlExamples: List<UrlExample>,
    delayTime: Duration = 5.seconds
) {
    var currentExampleIndex by remember { mutableStateOf(0) }

    // Automatically cycle through images every 3 seconds
    LaunchedEffect(key1 = true) {
        while (true) {
            delay(delayTime)
            currentExampleIndex = (currentExampleIndex + 1) % imageUrlExamples.size
        }
    }

    // Use AnimatedContent for smooth transitions
    AnimatedContent(
        targetState = currentExampleIndex,
        transitionSpec = {
            // Slide in from the right and slide out to the left
            slideInHorizontally { width -> width } togetherWith slideOutHorizontally { width -> -width }
        },
        label = "Image Carousel"
    ) { index ->
        val currentExample = imageUrlExamples[index]
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        ) {
            // Use the onState parameter to handle loading and error states
            var isLoading by remember { mutableStateOf(true) }
            var hasError by remember { mutableStateOf(false) }

            AsyncImage(
                model = currentExample.url,
                contentDescription = "Image URL Example",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                onState = { state ->
                    isLoading = state is AsyncImagePainter.State.Loading
                    hasError = state is AsyncImagePainter.State.Error
                }
            )

            // Show shimmer placeholder while loading
            if (isLoading) {
                ShimmerPlaceholder()
            }

            // Show error placeholder if loading fails
            if (hasError) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Failed to load image",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}




