package org.yassineabou.llms.app.core.sharedViews

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.yassineabou.llms.app.core.theme.colorSchemeCustom

@Composable
fun BlockLoader(
    modifier: Modifier = Modifier
) {
    val squareCount = 13
    val squareSize = 18.dp
    val spacing = 4.dp
    val activeColor = MaterialTheme.colorSchemeCustom.alwaysOrange

    val progress = remember { mutableStateListOf<Boolean>().apply {
        addAll(List(squareCount) { false })
    }}

    LaunchedEffect(Unit) {
        while (true) {
            for (i in 0 until squareCount) {
                progress[i] = true
                delay(400)
            }
            for (i in 0 until squareCount) {
                progress[i] = false
                delay(400)
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(spacing),
        modifier = modifier
    ) {
        repeat(squareCount) { index ->
            Box(
                modifier = Modifier
                    .size(squareSize)
                    .clip(RectangleShape)
                    .background(
                        color = if (progress[index])
                            activeColor
                        else Color.Transparent
                    )
                    .border(
                        width = 1.dp,
                        color = activeColor.copy(alpha = 0.2f),
                        shape = RectangleShape
                    )
            )
        }
    }
}