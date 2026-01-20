package org.yassineabou.llms.feature.imagine.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowSizeClass
import org.yassineabou.llms.app.core.sharedViews.BottomSheetContent
import org.yassineabou.llms.app.core.sharedViews.ModelDetails
import org.yassineabou.llms.app.core.sharedViews.ModelHeader
import org.yassineabou.llms.app.core.sharedViews.ModelTypeActionButtons
import org.yassineabou.llms.app.core.theme.colorSchemeCustom
import org.yassineabou.llms.feature.imagine.data.model.ImageGenModelList
import org.yassineabou.llms.feature.imagine.data.model.ImageModel
import org.yassineabou.llms.feature.imagine.data.model.ImageModelSection
import org.yassineabou.llms.feature.imagine.ui.ImagineViewModel
import org.yassineabou.llms.feature.imagine.ui.util.getColumnCount
import org.yassineabou.llms.feature.imagine.ui.util.getItemHeight
import org.yassineabou.llms.feature.imagine.ui.util.getMinItemWidth
import kotlin.math.ceil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageModelsBottomSheet(
    imagineViewModel: ImagineViewModel,
    onDismissRequest: () -> Unit,
    onAuthenticated: () -> Unit
) {
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val tempSelectedImageModel by imagineViewModel.tempSelectedImageModel.collectAsState()

    // Get window size class for responsive layout
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    var isInfoIconClicked by remember { mutableStateOf(false) }
    var infoImageModel by remember { mutableStateOf(tempSelectedImageModel) }

    LaunchedEffect(Unit) {
        imagineViewModel.setTempSelectedToSelected()
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
                            imagineViewModel.confirmSelectedImageModel()
                        }
                    )
                },
                body = {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        contentPadding = PaddingValues(bottom = 32.dp)
                    ) {
                        items(ImageGenModelList.groupedModels) { section ->
                            ImageModelType(
                                title = section.title,
                                subtitle = section.subtitle,
                                imageModelsList = section.models,
                                tempSelectedImageModel = tempSelectedImageModel,
                                onImageModelSelected = imagineViewModel::selectTempImageModel,
                                onInfoClick = { imageModel ->
                                    isInfoIconClicked = true
                                    infoImageModel = imageModel
                                },
                                windowSizeClass = windowSizeClass
                            )
                        }
                    }
                }
            )
        }
        AnimatedVisibility(isInfoIconClicked) {
            ModelDetails(
                title = infoImageModel.title,
                description = infoImageModel.description,
                image = infoImageModel.image,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onDismissRequest = { isInfoIconClicked = false }
            )
        }
    }
}

@Composable
private fun ImageModelType(
    title: String,
    subtitle: String,
    imageModelsList: List<ImageModel>,
    tempSelectedImageModel: ImageModel,
    onImageModelSelected: (ImageModel) -> Unit,
    onInfoClick: (ImageModel) -> Unit,
    windowSizeClass: WindowSizeClass
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 8.dp, end = 8.dp)
    ) {
        // Section Header
        Column(modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, end = 8.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Section Grid
        ImageModelGrid(
            models = imageModelsList,
            tempSelectedImageModel = tempSelectedImageModel,
            onImageModelSelected = onImageModelSelected,
            onInfoClick = onInfoClick,
            windowSizeClass = windowSizeClass
        )
    }
}

@Composable
private fun ImageModelGrid(
    models: List<ImageModel>,
    tempSelectedImageModel: ImageModel,
    onImageModelSelected: (ImageModel) -> Unit,
    onInfoClick: (ImageModel) -> Unit,
    windowSizeClass: WindowSizeClass
) {
    // Calculate columns based on window size
    val columnCount = getColumnCount(windowSizeClass)
    val minItemWidth = getMinItemWidth(windowSizeClass)
    val itemHeight = getItemHeight(windowSizeClass)

    // Calculate actual row count based on the number of columns
    val rowCount = ceil(models.size.toFloat() / columnCount).toInt()

    // Calculate grid height: (rows * itemHeight) + (spacing between rows)
    val spacingBetweenRows = 12 // dp
    val estimatedHeight = (rowCount * itemHeight + (rowCount - 1) * spacingBetweenRows).dp

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = minItemWidth),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        userScrollEnabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .height(estimatedHeight)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        items(
            items = models,
            key = { it.modelName }
        ) { item ->
            ImageModelItem(
                imageModel = item,
                isSelected = item == tempSelectedImageModel,
                onSelected = { onImageModelSelected(item) },
                onInfoClick = { onInfoClick(item) }
            )
        }
    }
}

@Composable
private fun ImageModelItem(
    imageModel: ImageModel,
    isSelected: Boolean,
    onSelected: () -> Unit,
    onInfoClick: () -> Unit
) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorSchemeCustom.alwaysBlue
    } else {
        Color.Transparent
    }

    val textColor = if (isSelected) {
        Color.White
    } else {
        MaterialTheme.colorScheme.onBackground
    }

    val borderColor = if (isSelected) {
        MaterialTheme.colorSchemeCustom.alwaysBlue
    } else {
        MaterialTheme.colorScheme.primary
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .background(backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable(onClick = onSelected)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        ModelHeader(
            image = imageModel.image,
            backgroundColor = backgroundColor,
            onInfoClick = { _ -> onInfoClick() }
        )

        Text(
            text = imageModel.title,
            color = textColor,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        EfficiencyBadge(
            modelName = imageModel.modelName,
            isSelected = isSelected
        )
    }
}

@Composable
private fun EfficiencyBadge(
    modelName: String,
    isSelected: Boolean
) {
    val badgeText = when (modelName) {
        "flux", "zimage" -> "~5K/pollen"
        "turbo" -> "~3.3K/pollen"
        "gptimage" -> "~75/pollen"
        "seedream" -> "~35/pollen"
        "kontext", "nanobanana", "seedream-pro" -> "~25/pollen"
        "nanobanana-pro" -> "~6/pollen"
        else -> null
    }

    badgeText?.let { text ->
        val badgeColor = if (isSelected) {
            Color.White.copy(alpha = 0.8f)
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        }

        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = badgeColor,
            maxLines = 1
        )
    }
}




