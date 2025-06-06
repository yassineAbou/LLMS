package org.yassineabou.llms.app.core.sharedViews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.yassineabou.llms.app.core.theme.colorSchemeCustom

@Composable
fun ModelTypeActionButtons(onDismissRequest: () -> Unit, onDone: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
    ) {
        Button(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = onDismissRequest
        ) {
            Text(text = "Cancel")
        }
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorSchemeCustom.alwaysBlue),
            modifier = Modifier
                .align(Alignment.CenterEnd),
            onClick = onDone
        ) {
            Text(
                color = Color.White,
                text = "Done"
            )
        }
    }
}