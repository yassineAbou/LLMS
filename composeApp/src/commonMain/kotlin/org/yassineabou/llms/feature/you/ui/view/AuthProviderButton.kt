package org.yassineabou.llms.feature.you.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class AuthProvider(
    val name: String,
    val iconRes: DrawableResource,
    val backgroundColor: Color,
    val textColor: Color,
    val iconPadding: PaddingValues = PaddingValues(0.dp),
    val onClick: () -> Unit
)

@Composable
fun AuthProviderButton(
    provider: AuthProvider,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = provider.onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = provider.backgroundColor,
            contentColor = provider.textColor
        ),
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(provider.iconPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(provider.iconRes),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                colorFilter = if (provider.name == "Passkey") ColorFilter.tint(MaterialTheme.colorScheme.onBackground) else null
            )
            Text(
                text = provider.name,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}