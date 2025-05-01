package org.yassineabou.playground.feature.chat.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import org.yassineabou.playground.feature.chat.data.model.TextModel
import org.yassineabou.playground.feature.chat.data.model.textGenModelList
import org.yassineabou.playground.feature.chat.ui.ChatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextModelsBottomSheet(
    chatViewModel: ChatViewModel,
    onDismissRequest: () -> Unit,
    onAuthenticated: () -> Unit
) {
    val tempSelectedTextModel by chatViewModel.tempSelectedTextModel.collectAsState()
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
                            chatViewModel.handleModelSelectionChange()
                            onAuthenticated()
                        }
                    )
                },
                body = {
                    TextModelType(
                        textModelsList = textGenModelList,
                        tempSelectedTextModel = tempSelectedTextModel,
                        onTextModelSelected = { chatViewModel.selectTempTextModel(it) },
                        onInfoClick = { textModel ->
                            isInfoIconClicked = true
                            infoTextModel = textModel
                        }
                    )
                }
            )
        }
        AnimatedVisibility(isInfoIconClicked) {
            ModelDetails(
                title = tempSelectedTextModel.title,
                description = tempSelectedTextModel.description,
                image = tempSelectedTextModel.image,
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .padding(horizontal = 16.dp),
                onDismissRequest = { isInfoIconClicked = false }
            )
        }
    }
}

@Composable
private fun TextModelType(
    textModelsList: List<TextModel>,
    tempSelectedTextModel: TextModel,
    onTextModelSelected: (TextModel) -> Unit,
    onInfoClick: (TextModel) -> Unit
) {
     LazyVerticalGrid(
         columns = GridCells.Adaptive(minSize = 150.dp),
         verticalArrangement = Arrangement.spacedBy(12.dp),
         horizontalArrangement = Arrangement.spacedBy(12.dp),
         modifier =  Modifier
             .fillMaxSize()
             .padding(vertical = 8.dp, horizontal = 16.dp)
     ) {
         items(textModelsList) { item ->
             TextModelItem(
                 tempSelectedTextModel = item,
                 isSelected = item == tempSelectedTextModel,
                 onSelected = { onTextModelSelected(item) },
                 onInfoClick = { onInfoClick(item) }
             )
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
        ModelHeader(
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


