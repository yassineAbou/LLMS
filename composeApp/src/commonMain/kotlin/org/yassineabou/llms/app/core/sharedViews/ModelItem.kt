package org.yassineabou.llms.app.core.sharedViews

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.yassineabou.llms.app.core.theme.colorSchemeCustom

/**
 * Shared composable for model selection items used in both
 * TextModelsBottomSheet and ImageModelsBottomSheet.
 */
@Composable
fun ModelItem(
    title: String,
    efficiency: String?,
    isSelected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
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
        MaterialTheme.colorScheme.outlineVariant
    }

    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .background(backgroundColor, shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onSelected)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = title,
            color = textColor,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        EfficiencyBadge(
            efficiency = efficiency,
            isSelected = isSelected
        )
    }
}