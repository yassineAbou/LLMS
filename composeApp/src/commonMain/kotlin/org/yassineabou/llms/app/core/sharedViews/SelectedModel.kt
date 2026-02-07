package org.yassineabou.llms.app.core.sharedViews

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectedModel(
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
        )

        Spacer(modifier = Modifier.width(4.dp))

        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "Select model",
            tint = Color.White,
            modifier = Modifier.size(18.dp)
        )
    }
}