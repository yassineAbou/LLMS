package org.yassineabou.playground.feature.Imagine.view

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom
import org.yassineabou.playground.app.ui.view.BottomSheetContent
import org.yassineabou.playground.app.ui.view.GenTypesButtons
import org.yassineabou.playground.feature.Imagine.model.ImageGenModelList
import org.yassineabou.playground.feature.Imagine.model.ImageModel
import org.yassineabou.playground.feature.Imagine.model.UrlExample
import org.yassineabou.playground.feature.Imagine.ui.ImageGenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageGenTypesBottomSheet(
    imageGenViewModel: ImageGenViewModel = koinViewModel(),
    onDismissRequest: () -> Unit,
    onAuthenticated: () -> Unit
) {
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val tempSelectedImageModel by imageGenViewModel.tempSelectedImageModel.collectAsState()


    LaunchedEffect(Unit) {
        imageGenViewModel.setTempSelectedToSelected()
    }


    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        BottomSheetContent(
            title = {
                GenTypesButtons(
                    onDismissRequest = onDismissRequest,
                    onAuthenticated = {
                        onAuthenticated()
                        imageGenViewModel.confirmSelectedImageModel()
                    }
                )
            },
            body = {
               LazyColumn(modifier = Modifier.fillMaxSize()) {
                   item {
                       ImageGenTypes(
                           type = "Generalist",
                           imageModelsList = ImageGenModelList.generalist,
                           tempSelectedImageModel = tempSelectedImageModel,
                           onImageModelSelected = imageGenViewModel::selectTempImageModel
                       )
                   }
                   item {
                       ImageGenTypes(
                           type = "Realistic",
                           imageModelsList = ImageGenModelList.realistic,
                           tempSelectedImageModel = tempSelectedImageModel,
                           onImageModelSelected = imageGenViewModel::selectTempImageModel
                       )
                   }
                   item {
                       ImageGenTypes(
                           type = "Anime",
                           imageModelsList = ImageGenModelList.anime,
                           tempSelectedImageModel = tempSelectedImageModel,
                           onImageModelSelected = imageGenViewModel::selectTempImageModel
                       )
                   }
                   item {
                       ImageGenTypes(
                           type = "3D",
                           imageModelsList = ImageGenModelList.threeD,
                           tempSelectedImageModel = tempSelectedImageModel,
                           onImageModelSelected = imageGenViewModel::selectTempImageModel
                       )
                   }
                   item {
                       ImageGenTypes(
                           type = "Comic",
                           imageModelsList = ImageGenModelList.comic,
                           tempSelectedImageModel = tempSelectedImageModel,
                           onImageModelSelected = imageGenViewModel::selectTempImageModel
                       )
                   }
                   item {
                       ImageGenTypes(
                           type = "Furry",
                           imageModelsList = ImageGenModelList.furry,
                           tempSelectedImageModel = tempSelectedImageModel,
                           onImageModelSelected = imageGenViewModel::selectTempImageModel
                       )
                   }
               }
            }
        )
    }
}


@Composable
private fun ImageGenTypes(
    imageModelsList: List<ImageModel>,
    type: String,
    tempSelectedImageModel: ImageModel,
    onImageModelSelected: (ImageModel) -> Unit
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
            modifier = Modifier.padding(top = 4.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(imageModelsList) { item ->
                imageGenModel(
                    tempSelectedImageModel = item,
                    isSelected = item == tempSelectedImageModel,
                    onSelected = { onImageModelSelected(item) }
                )
            }

        }

    }
}

@Composable
private fun imageGenModel(
    tempSelectedImageModel: ImageModel,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    val backgroundColor = if (isSelected) MaterialTheme.colorSchemeCustom.alwaysBlue else Color.Transparent
    val textColor = if (isSelected) MaterialTheme.colorSchemeCustom.alwaysWhite else MaterialTheme.colorScheme.onBackground
    Column(
        modifier = Modifier
            .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(16.dp))
            .background(backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable(onClick = onSelected)
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp))
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
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = tempSelectedImageModel.title,
            color = textColor,
            style = MaterialTheme.typography.bodyLarge,
        )
        Text(
            text = tempSelectedImageModel.description,
            maxLines = 1,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(top = 5.dp)
                .width(175.dp)
                .basicMarquee(iterations = Int.MAX_VALUE),
        )
    }
}





@Composable
private fun ImageCarousel(imageUrlExamples: List<UrlExample>) {
    var currentExampleIndex by remember { mutableStateOf(0) }
    var currentExample by remember { mutableStateOf(imageUrlExamples.first()) }

    LaunchedEffect(key1 = true) {
        while (true) {
            delay(4000)
            currentExampleIndex = (currentExampleIndex + 1) % imageUrlExamples.size
            currentExample = imageUrlExamples[currentExampleIndex]
        }
    }


    KamelImage(
        resource = { asyncPainterResource(data = currentExample.url) },
        contentDescription = "image url example",
        contentScale = ContentScale.Crop,
        animationSpec = tween(1000),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    )
}





