package org.yassineabou.playground.app.core.sharedViews


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun PyramidTextFormat(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    style: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.bodyLarge
) {
    val lines = text.split("\n")

    val windowSizeClass = calculateWindowSizeClass()
    val pyramidDepth = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 2
        WindowWidthSizeClass.Medium -> 3
        WindowWidthSizeClass.Expanded -> 4
        else -> 3
    }


    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            lines.forEachIndexed { index, line ->
                val leftPadding = 16.dp * (pyramidDepth - index - 1)
                SelectionContainer {
                    Text(
                        text = line,
                        modifier = Modifier.padding(start = leftPadding),
                        textAlign = TextAlign.Center,
                        style = style,
                        color = color
                    )
                }
            }
        }
    }
}





