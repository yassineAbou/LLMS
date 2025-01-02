package org.yassineabou.playground.feature.chat.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.app.ui.theme.colorSchemeCustom
import org.yassineabou.playground.app.ui.view.BottomSheetContent
import org.yassineabou.playground.app.ui.view.GenTypesButtons
import org.yassineabou.playground.feature.chat.model.TextModel
import org.yassineabou.playground.feature.chat.model.textGenModelList
import org.yassineabou.playground.feature.chat.ui.ChatViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun TextGenTypesBottomSheet(
    textGenViewModel: ChatViewModel = koinViewModel(),
    onDismissRequest: () -> Unit,
    onAuthenticated: () -> Unit
) {
    val tempSelectedTextModel by textGenViewModel.tempSelectedTextModel.collectAsState()
    val selectedTextModel by textGenViewModel.selectedTextModel.collectAsState()
    var isInfoIconClicked by remember { mutableStateOf(false) }
    var infoTextModel by remember { mutableStateOf(tempSelectedTextModel) }
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val windowSizeClass = calculateWindowSizeClass()
    val columnCount = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 2
        else -> 3
    }

    LaunchedEffect(Unit) {
        textGenViewModel.setTempSelectedToSelected()
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        AnimatedVisibility(!isInfoIconClicked) {
            BottomSheetContent(
                title = {
                    GenTypesButtons(
                        onDismissRequest = onDismissRequest,
                        onDone = {
                            onAuthenticated()
                            textGenViewModel.confirmSelectedTextModel()
                            if (tempSelectedTextModel.title != selectedTextModel.title) {
                                textGenViewModel.startNewChat()
                            }
                        }
                    )
                },
                body = {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(count = columnCount),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(textGenModelList) { item ->
                            TextGenType(
                                tempSelectedTextModel = item,
                                isSelected = item == tempSelectedTextModel,
                                onSelected = { textGenViewModel.selectTempTextModel(item) },
                                onInfoClick = {
                                    isInfoIconClicked = it
                                    infoTextModel = item
                                }
                            )
                        }
                    }
                }
            )
        }
        AnimatedVisibility(isInfoIconClicked) {
            TextGenInformation(
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
private fun TextGenType(
    tempSelectedTextModel: TextModel,
    isSelected: Boolean,
    onSelected: () -> Unit,
    onInfoClick: (Boolean) -> Unit,
) {
    val backgroundColor = if (isSelected) MaterialTheme.colorSchemeCustom.alwaysBlue else Color.Transparent
    val textColor = if (isSelected) MaterialTheme.colorSchemeCustom.alwaysWhite else MaterialTheme.colorScheme.onBackground
    Column(
        modifier = Modifier
            .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(16.dp))
            .background(backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable(onClick = onSelected)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ModelProviderImage(
            provider = tempSelectedTextModel.provider,
            image = tempSelectedTextModel.image,
            textColor = textColor,
            backgroundColor = backgroundColor
        )
        Text(
            text = tempSelectedTextModel.title,
            color = textColor,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        )
        InfoIconButton(
            modifier = Modifier.align(Alignment.End),
            onInfoClick = onInfoClick
        )
    }
}

@Composable
private fun ModelProviderImage(
    provider: String,
    textColor: Color,
    backgroundColor: Color,
    image: DrawableResource
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "Text Model",
            modifier = Modifier.size(50.dp)
        )
        Text(
            text = provider,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold,
            color = textColor,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
private fun InfoIconButton(
    modifier: Modifier = Modifier,
    onInfoClick: (Boolean) -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = { onInfoClick(true) }
    ) {
        Icon(
            imageVector = Icons.TwoTone.Info,
            contentDescription = "info",
        )
    }
}

