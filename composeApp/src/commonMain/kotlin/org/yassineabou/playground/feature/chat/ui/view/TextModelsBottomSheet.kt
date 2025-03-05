package org.yassineabou.playground.feature.chat.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.playground.app.core.sharedViews.BottomSheetContent
import org.yassineabou.playground.app.core.sharedViews.ModelTypeActionButtons
import org.yassineabou.playground.app.core.sharedViews.InfoIconButton
import org.yassineabou.playground.app.core.sharedViews.ModelInformation
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.app.core.util.draggableScrollModifier
import org.yassineabou.playground.feature.chat.model.TextGenModelList
import org.yassineabou.playground.feature.chat.model.TextModel
import org.yassineabou.playground.feature.chat.ui.ChatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextModelsBottomSheet(
    chatViewModel: ChatViewModel,
    onDismissRequest: () -> Unit,
    onAuthenticated: () -> Unit
) {
    val tempSelectedTextModel by chatViewModel.tempSelectedTextModel.collectAsState()
    val selectedTextModel by chatViewModel.selectedTextModel.collectAsState()
    var isInfoIconClicked by remember { mutableStateOf(false) }
    var infoTextModel by remember { mutableStateOf(tempSelectedTextModel) }
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

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
                            val modelChanged = tempSelectedTextModel != selectedTextModel
                            if (modelChanged) {
                                // Only finalize if there's existing content
                                if (chatViewModel.currentChatMessages.isNotEmpty()) {
                                    chatViewModel.finalizeCurrentChat()
                                }
                                chatViewModel.confirmSelectedTextModel()

                                // Only start new chat if there was previous content
                                if (chatViewModel.currentChatMessages.isNotEmpty()) {
                                    chatViewModel.startNewChat(forceNew = true)
                                } else {
                                    chatViewModel.resetCurrentChat()
                                }
                            } else {
                                chatViewModel.confirmSelectedTextModel()
                            }
                            onAuthenticated()
                        }
                    )
                },
                body = {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(bottom = 16.dp)
                    ) {
                        item {
                            TextModelType(
                                type = "DeepSeek",
                                textModelsList = TextGenModelList.deepSeek,
                                tempSelectedTextModel = tempSelectedTextModel,
                                onTextModelSelected = { chatViewModel.selectTempTextModel(it) },
                                onInfoClick = { textModel ->
                                    isInfoIconClicked = true
                                    infoTextModel = textModel
                                }
                            )
                        }
                        item {
                            TextModelType(
                                type = "Alibaba Cloud",
                                textModelsList = TextGenModelList.alibabaCloud,
                                tempSelectedTextModel = tempSelectedTextModel,
                                onTextModelSelected = { chatViewModel.selectTempTextModel(it) },
                                onInfoClick = { textModel ->
                                    isInfoIconClicked = true
                                    infoTextModel = textModel
                                }
                            )
                        }
                        item {
                            TextModelType(
                                type = "Meta",
                                textModelsList = TextGenModelList.meta,
                                tempSelectedTextModel = tempSelectedTextModel,
                                onTextModelSelected = { chatViewModel.selectTempTextModel(it) },
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
            ModelInformation(
                textModel = infoTextModel,
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .padding(horizontal = 16.dp),
                onDismissRequest = { isInfoIconClicked = false }
            )
        }
    }
}

@Composable
private fun TextModelType(
    textModelsList: List<TextModel>,
    type: String,
    tempSelectedTextModel: TextModel,
    onTextModelSelected: (TextModel) -> Unit,
    onInfoClick: (TextModel) -> Unit
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
            items(textModelsList) { item ->
                TextModelItem(
                    tempSelectedTextModel = item,
                    isSelected = item == tempSelectedTextModel,
                    onSelected = { onTextModelSelected(item) },
                    onInfoClick =  { onInfoClick(item) }
                )
            }

        }
    }
}


@Composable
private fun TextModelItem(
    tempSelectedTextModel: TextModel,
    isSelected: Boolean,
    onSelected: () -> Unit,
    onInfoClick: (Boolean) -> Unit,
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
            .clickable(onClick = onSelected)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ModelInformation(
            image = tempSelectedTextModel.image,
            backgroundColor = backgroundColor,
            onInfoClick =  { onInfoClick(true) }
        )
        Text(
            text = tempSelectedTextModel.title,
            color = textColor,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        )
    }
}

@Composable
private fun ModelInformation(
    backgroundColor: Color,
    image: DrawableResource,
    onInfoClick: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "Text Model",
            modifier = Modifier.size(40.dp)
        )
        InfoIconButton(
            onInfoClick = { onInfoClick(true) }
        )
    }
}

