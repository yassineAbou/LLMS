package org.yassineabou.llms.feature.chat.ui.listDetailPane

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun NewChatButton(
    onNewChatClick: () -> Unit
) {
    Button(
        modifier = Modifier.padding(16.dp),
        onClick = onNewChatClick,
        shape = RoundedCornerShape(16.dp), // More rounded corners
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer, // Use primary container for a softer look
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer // Text/icon color
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp, // Slightly more elevation for depth
            pressedElevation = 4.dp
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp) // More vertical padding
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp) // Increase spacing between icon and text
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "New Chat",
                modifier = Modifier.size(24.dp) // Slightly larger icon
            )
            Text(
                text = "New Chat",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}