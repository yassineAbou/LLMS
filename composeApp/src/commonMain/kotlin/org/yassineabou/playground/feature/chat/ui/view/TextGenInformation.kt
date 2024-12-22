package org.yassineabou.playground.feature.chat.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.playground.feature.chat.model.TextModel

@Composable
fun TextGenInformation(
    textModel: TextModel,
    modifier: Modifier = Modifier,
    onDismissRequest: (Boolean) -> Unit,
) {
    Box(
        modifier = modifier
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Image(
                painter = painterResource(textModel.image),
                contentDescription = "Text Model",
                modifier = Modifier.size(65.dp)
            )
            Text(
                text = textModel.title,
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.displaySmall
            )
            Text(
                text = textModel.description,
                fontWeight = FontWeight.Medium,
            )
        }
        GotItButton(
            modifier =  Modifier.Companion
                .align(alignment = Alignment.BottomStart)
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            onDismissRequest = onDismissRequest
        )
    }
}

@Composable
private fun GotItButton(
    modifier: Modifier = Modifier,
    onDismissRequest: (Boolean) -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onDismissRequest(false) }
    ) {
        Text(
            text = "Got it!",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 12.dp),
        )
    }
}


