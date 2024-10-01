package org.yassineabou.playground.app.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetContent(
    title: String? = null,
    body: (@Composable ColumnScope.() -> Unit)? = null,
    actionContent: @Composable (ColumnScope.() -> Unit)? = null,
    footerContent: @Composable (ColumnScope.() -> Unit)? = null,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (title != null) {
            BottomSheetTitle(text = title)
        }
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
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.onSurface,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineLarge,
    )
}

@Composable
fun BottomSheetButton(
    text: String,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    onAuthenticated: () -> Unit,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        onClick = onAuthenticated
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun PrivacyInfoSection(
) {
    Column(modifier = Modifier.padding(start = 32.dp, end = 32.dp, bottom = 16.dp).navigationBarsPadding()) {
        InfoText(
            title = "Why do you require to login?",
            description = """
                The anti-piracy defense: Using Google and Apple sign-in prevents modded apps accessing your data, improving overall security.
                Synchronization: Using Google and Apple sign-in allows the data to be synced across devices and to be saved on the cloud, so they are cached even if the app gets uninstalled.
            """.trimIndent()
        )
        InfoText(
            title = "Will my data be sold?",
            description = "No, the only scope request is email and username. Because the app receives no private user information, it cannot sell your data."
        )
        InfoText(
            title = "What data is stored?",
            description = "The in-app content (texts, images, etc.) is stored on the server, but everything else is not."
        )
        InfoText(
            title = "Is my user identify anonymous?",
            description = "Yes, the user identity is anonymous and it can't be linked back to the user."
        )
        InfoText(
            title = "Can I delete my data?",
            description = "Yes, you can delete your account, which will delete all your non-anonymous data. Go to Settings > Delete Account."
        )
    }
}

@Composable
private fun InfoText(title: String, description: String) {
    Text(
        text = title,
        modifier = Modifier.padding(top = 12.dp),
        fontWeight = FontWeight.SemiBold,
        style = MaterialTheme.typography.titleSmall,
    )
    Text(
        text = description,
        fontWeight = FontWeight.Light,
        textAlign = TextAlign.Justify,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
fun TermsOfServiceAndPrivacyPolicy(
    modifier: Modifier = Modifier,
    leadingItemTitle: String? = null,
    trailingItemTitle: String? = null,
    onLeadingItemClick: () -> Unit = {},
    onTrailingItemClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        val uriHandler = LocalUriHandler.current
        if (leadingItemTitle != null) {
            TextLink(
                text = leadingItemTitle,
                onClick = onLeadingItemClick
            )
            TextSeparator()
        }
        TextLink(
            text = "Terms",
            onClick = {  }
        )
        TextSeparator()
        TextLink(
            text = "Privacy Policy",
            onClick = {  }
        )
        if (trailingItemTitle != null) {
            TextSeparator()
            TextLink(
                text = trailingItemTitle,
                onClick = onTrailingItemClick
            )
        }
    }
}

@Composable
private fun TextLink(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        modifier = Modifier.clickable(onClick = onClick),
        fontWeight = FontWeight.Light,
        textDecoration = TextDecoration.Underline,
        style = MaterialTheme.typography.labelLarge
    )
}

@Composable
private fun TextSeparator() {
    Text(
        text = "|",
        fontWeight = FontWeight.Light,
        style = MaterialTheme.typography.labelLarge
    )
}