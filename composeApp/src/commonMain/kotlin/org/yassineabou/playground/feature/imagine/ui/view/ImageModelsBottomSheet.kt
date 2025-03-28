package org.yassineabou.playground.feature.imagine.ui.view

import androidx.compose.animation.AnimatedVisibility
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
import com.github.panpf.sketch.AsyncImage
import org.yassineabou.playground.app.core.sharedViews.BottomSheetContent
import org.yassineabou.playground.app.core.sharedViews.InfoIconButton
import org.yassineabou.playground.app.core.sharedViews.ModelInformation
import org.yassineabou.playground.app.core.sharedViews.ModelTypeActionButtons
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.app.core.util.draggableScrollModifier
import org.yassineabou.playground.feature.imagine.model.ImageGenModelList
import org.yassineabou.playground.feature.imagine.model.ImageModel
import org.yassineabou.playground.feature.imagine.model.UrlExample
import org.yassineabou.playground.feature.imagine.ui.ImageGenViewModel

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
                        item(key = "Generalist") {
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
                        item(key = "Realistic") {
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
                        item(key = "Anime") {
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
                        item(key = "3D") {
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
                        item(key = "Comic") {
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
                        item(key = "Furry") {
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
            items(
                key = { item -> item.id },
                items = imageModelsList
            ) { item ->
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

            ImageModelExample(urlExample = tempSelectedImageModel.urlExamples.random())

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
private fun ImageModelExample(urlExample: UrlExample) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            uri = urlExample.url,
            contentDescription = urlExample.description,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,

        )
    }
}




