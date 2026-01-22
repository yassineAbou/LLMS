package org.yassineabou.llms.feature.chat.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowSizeClass
import org.yassineabou.llms.app.core.sharedViews.BottomSheetContent
import org.yassineabou.llms.app.core.sharedViews.EfficiencyBadge
import org.yassineabou.llms.app.core.sharedViews.ModelDetails
import org.yassineabou.llms.app.core.sharedViews.ModelHeader
import org.yassineabou.llms.app.core.sharedViews.ModelTypeActionButtons
import org.yassineabou.llms.app.core.theme.colorSchemeCustom
import org.yassineabou.llms.app.core.util.ModelGridUtils.calculateGridHeight
import org.yassineabou.llms.app.core.util.ModelGridUtils.getMinItemWidth
import org.yassineabou.llms.feature.chat.data.model.TextGenModelList
import org.yassineabou.llms.feature.chat.data.model.TextModel
import org.yassineabou.llms.feature.chat.ui.ChatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextModelsBottomSheet(
    chatViewModel: ChatViewModel,
    onDismissRequest: () -> Unit,
    onAuthenticated: () -> Unit
) {
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val tempSelectedTextModel by chatViewModel.tempSelectedTextModel.collectAsState()

    var isInfoIconClicked by remember { mutableStateOf(false) }
    var infoTextModel by remember { mutableStateOf(tempSelectedTextModel) }

    LaunchedEffect(Unit) {
        chatViewModel.setTempSelectedToSelected()
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
                            chatViewModel.handleModelSelectionChange()
                            onAuthenticated()
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
                        items(TextGenModelList.groupedModels) { section ->
                            TextModelType(
                                title = section.title,
                                subtitle = section.subtitle,
                                textModelsList = section.models,
                                tempSelectedTextModel = tempSelectedTextModel,
                                onTextModelSelected = chatViewModel::selectTempTextModel,
                                onInfoClick = { textModel ->
                                    isInfoIconClicked = true
                                    infoTextModel = textModel
                                }
                            )
                        }
                    }
                }
            )
        }
        AnimatedVisibility(isInfoIconClicked) {
            ModelDetails(
                title = infoTextModel.title,
                description = infoTextModel.description,
                image = infoTextModel.image,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onDismissRequest = { isInfoIconClicked = false }
            )
        }
    }
}

@Composable
private fun TextModelType(
    title: String,
    subtitle: String,
    textModelsList: List<TextModel>,
    tempSelectedTextModel: TextModel,
    onTextModelSelected: (TextModel) -> Unit,
    onInfoClick: (TextModel) -> Unit
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
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
        TextModelGrid(
            models = textModelsList,
            tempSelectedTextModel = tempSelectedTextModel,
            onTextModelSelected = onTextModelSelected,
            onInfoClick = onInfoClick,
            windowSizeClass = windowSizeClass
        )
    }
}

@Composable
private fun TextModelGrid(
    models: List<TextModel>,
    tempSelectedTextModel: TextModel,
    onTextModelSelected: (TextModel) -> Unit,
    onInfoClick: (TextModel) -> Unit,
    windowSizeClass: WindowSizeClass
) {
    val minItemWidth = getMinItemWidth(windowSizeClass)
    val estimatedHeight = calculateGridHeight(models.size, windowSizeClass)

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
            TextModelItem(
                textModel = item,
                isSelected = item == tempSelectedTextModel,
                onSelected = { onTextModelSelected(item) },
                onInfoClick = { onInfoClick(item) }
            )
        }
    }
}

@Composable
private fun TextModelItem(
    textModel: TextModel,
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
            image = textModel.image,
            backgroundColor = backgroundColor,
            onInfoClick = { _ -> onInfoClick() }
        )

        Text(
            text = textModel.title,
            color = textColor,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        EfficiencyBadge(
            efficiency = textModel.efficiency,
            isSelected = isSelected
        )
    }
}




