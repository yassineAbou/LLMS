package org.yassineabou.llms.feature.imagine.ui.view

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
import org.yassineabou.llms.feature.imagine.data.model.ImageGenModelList
import org.yassineabou.llms.feature.imagine.data.model.ImageModel
import org.yassineabou.llms.feature.imagine.ui.ImagineViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageModelsBottomSheet(
    imagineViewModel: ImagineViewModel,
    onDismissRequest: () -> Unit,
    onAuthenticated: () -> Unit
) {
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val tempSelectedImageModel by imagineViewModel.tempSelectedImageModel.collectAsState()

    LaunchedEffect(Unit) {
        imagineViewModel.setTempSelectedToSelected()
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
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {
                    items(ImageGenModelList.groupedModels) { section ->
                        ImageModelSection(
                            title = section.title,
                            subtitle = section.subtitle,
                            imageModelsList = section.models,
                            tempSelectedImageModel = tempSelectedImageModel,
                            onImageModelSelected = imagineViewModel::selectTempImageModel,
                        )
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ImageModelSection(
    title: String,
    subtitle: String,
    imageModelsList: List<ImageModel>,
    tempSelectedImageModel: ImageModel,
    onImageModelSelected: (ImageModel) -> Unit,
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
            imageModelsList.forEach { item ->
                ModelItem(
                    title = item.title,
                    efficiency = item.efficiency,
                    isSelected = item == tempSelectedImageModel,
                    onSelected = { onImageModelSelected(item) },
                    modifier = Modifier.weight(1f)
                )
            }
            val remainder = imageModelsList.size % columns
            if (remainder != 0) {
                repeat(columns - remainder) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}