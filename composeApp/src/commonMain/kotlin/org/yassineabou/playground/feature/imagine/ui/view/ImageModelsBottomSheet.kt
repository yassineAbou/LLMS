package org.yassineabou.playground.feature.imagine.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.yassineabou.playground.app.core.sharedViews.BottomSheetContent
import org.yassineabou.playground.app.core.sharedViews.ModelDetails
import org.yassineabou.playground.app.core.sharedViews.ModelHeader
import org.yassineabou.playground.app.core.sharedViews.ModelTypeActionButtons
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.feature.imagine.model.ImageGenModelList
import org.yassineabou.playground.feature.imagine.model.ImageModel
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
                    ImageModelType(
                        imageModelsList = ImageGenModelList.newImageModel,
                        tempSelectedImageModel = tempSelectedImageModel,
                        onImageModelSelected = imageGenViewModel::selectTempImageModel,
                        onInfoClick = { imageModel ->
                            isInfoIconClicked = true
                            infoImageModel = imageModel
                        }
                    )
                }
            )
        }
        AnimatedVisibility(isInfoIconClicked) {
            ModelDetails(
                title = infoImageModel.title,
                description = infoImageModel.description,
                image = infoImageModel.image,
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .padding(horizontal = 16.dp),
                onDismissRequest = { isInfoIconClicked = false }
            )
        }
    }
}


@Composable
private fun ImageModelType(
    imageModelsList: List<ImageModel>,
    tempSelectedImageModel: ImageModel,
    onImageModelSelected: (ImageModel) -> Unit,
    onInfoClick: (ImageModel) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier =  Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
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
        if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground
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
        ModelHeader(
            image = tempSelectedImageModel.image,
            backgroundColor = backgroundColor,
            onInfoClick = { onInfoClick() }
        )
        Text(
            text = tempSelectedImageModel.title,
            color = textColor,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        )
    }
}




