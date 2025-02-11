package org.yassineabou.playground.app.core.sharedViews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetContent(
    title: (@Composable ColumnScope.() -> Unit)? = null,
    body: (@Composable ColumnScope.() -> Unit)? = null,
    actionContent: @Composable (ColumnScope.() -> Unit)? = null,
    footerContent: @Composable (ColumnScope.() -> Unit)? = null,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        title?.invoke(this)
        body?.invoke(this)
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                actionContent?.invoke(this)
            }
        }
        footerContent?.invoke(this)

    }
}

@Composable
fun BottomSheetTitle(
    text: String,
    style: TextStyle = MaterialTheme.typography.headlineLarge,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        style = style,
    )
}

@Composable
fun BottomSheetButton(
    text: String,
    icon: ImageVector? = null, // Make icon optional
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    style: TextStyle = MaterialTheme.typography.titleLarge,
    onAuthenticated: () -> Unit,
) {
    Button(
        onClick = {
            onAuthenticated()
        },
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
        )
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = text,
                tint = contentColor,
                modifier = Modifier.size(24.dp) // Set size for the icon
            )
            Spacer(modifier = Modifier.width(8.dp)) // Space between icon and text
        }
        Text(
            text = text,
            color = contentColor,
            style = style,
            modifier = Modifier.padding(8.dp)
        )
    }
}

