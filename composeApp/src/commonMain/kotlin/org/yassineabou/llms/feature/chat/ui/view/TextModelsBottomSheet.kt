package org.yassineabou.llms.feature.chat.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.yassineabou.llms.app.core.sharedViews.BottomSheetContent
import org.yassineabou.llms.app.core.sharedViews.EfficiencyBadge
import org.yassineabou.llms.app.core.sharedViews.ModelItem
import org.yassineabou.llms.app.core.sharedViews.ModelTypeActionButtons
import org.yassineabou.llms.app.core.theme.colorSchemeCustom
import org.yassineabou.llms.app.core.util.ModelGridUtils.getColumnCount
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

    LaunchedEffect(Unit) {
        chatViewModel.setTempSelectedToSelected()
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
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
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(TextGenModelList.groupedModels) { section ->
                        TextModelSection(
                            title = section.title,
                            subtitle = section.subtitle,
                            textModelsList = section.models,
                            tempSelectedTextModel = tempSelectedTextModel,
                            onTextModelSelected = chatViewModel::selectTempTextModel,
                        )
                    }
                }
            }
        )
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TextModelSection(
    title: String,
    subtitle: String,
    textModelsList: List<TextModel>,
    tempSelectedTextModel: TextModel,
    onTextModelSelected: (TextModel) -> Unit,
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val columns = getColumnCount(windowSizeClass)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachRow = columns
        ) {
            textModelsList.forEach { item ->
                ModelItem(
                    title = item.title,
                    efficiency = item.efficiency,
                    isSelected = item == tempSelectedTextModel,
                    onSelected = { onTextModelSelected(item) },
                    modifier = Modifier.weight(1f)
                )
            }
            val remainder = textModelsList.size % columns
            if (remainder != 0) {
                repeat(columns - remainder) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}



