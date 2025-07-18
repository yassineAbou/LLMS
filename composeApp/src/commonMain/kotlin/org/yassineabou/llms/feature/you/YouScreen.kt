package org.yassineabou.llms.feature.you

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_apple
import llms.composeapp.generated.resources.ic_google
import llms.composeapp.generated.resources.ic_pass_key
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.llms.app.core.util.PlatformConfig
import org.yassineabou.llms.app.core.util.isAndroid
import org.yassineabou.llms.app.core.util.isIos
import org.yassineabou.llms.feature.imagine.ui.util.rememberIsLargeScreen
import org.yassineabou.llms.feature.you.view.*


// State container that manages authentication state
@Composable
fun YouScreen(youViewModel: YouViewModel) {
    val isLoggedIn by youViewModel.isLoggedIn.collectAsStateWithLifecycle()

    YouContent(
        isLoggedIn = isLoggedIn,
        onLogin = {
            youViewModel.onLogin()
        },
        onLogout = { youViewModel.onLogout() }
    )
}

// Main screen composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YouContent(
    isLoggedIn: Boolean,
    onLogin: () -> Unit,
    onLogout: () -> Unit
) {
    val isLargeScreen = rememberIsLargeScreen()
    val horizontalPadding = if (isLargeScreen) 48.dp else 16.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = horizontalPadding, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Heroic container transform animation for login state
        AnimatedVisibility(
            visible = isLoggedIn,
            enter = AuthScreenTransition.heroContentEnter,
            exit = AuthScreenTransition.heroContentExit
        ) {
            ProfileContent(onLogout = onLogout)
        }

        AnimatedVisibility(
            visible = !isLoggedIn,
            enter = AuthScreenTransition.heroContentEnter,
            exit = AuthScreenTransition.heroContentExit
        ) {
            LoginPromptContent(onLogin = onLogin)
        }

    }
}

// Profile content
@Composable
fun ProfileContent(
    onLogout: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        ProfileHeader(
            name = "User",
            modifier = Modifier.padding(top = 24.dp)
        )

        ResponsiveButtonContainer(
            modifier = Modifier.padding(top = 24.dp)
        ) {
            AuthButton(
                onClick = onLogout,
                text = "Log out",
                icon = Icons.AutoMirrored.Filled.ExitToApp,
                maxWidth = 400.dp,
                widthFraction = if (rememberIsLargeScreen()) 0.6f else 0.5f,
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        AccountCard(
            label = "U",
            email = "User@gmail.com",
            onLogout = onLogout,
            modifier = Modifier.padding(top = 32.dp)
        )

        VerifiedUserAnimation(modifier = Modifier.size(200.dp))

        AuthenticationSuccessContent()


    }
}

// User profile header
@Composable
private fun ProfileHeader(
    name: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserInitials(
            name = name,
            modifier = Modifier.size(72.dp)
        )

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(name, style = MaterialTheme.typography.titleLarge)
            Text(
                text = "@${name}",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 16.sp
            )
        }
    }
}

// Google account integration card
@Composable
private fun AccountCard(
    label: String,
    email: String,
    modifier: Modifier = Modifier,
    onLogout: () -> Unit,
) {
    AdaptiveLayout(modifier = modifier) {
        Card(
            modifier = Modifier
                .widthIn(max = 600.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AccountAvatar(
                    label = label,
                    modifier = Modifier.size(40.dp)
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                ) {
                    Text("Account", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = email,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                RemoveAccountButton(onLogout)
            }
        }
    }
}

// Success state content
@Composable
private fun AuthenticationSuccessContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 24.dp)
    ) {
        Text(
            "Account Verified",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            "Your data is securely synced across all devices",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

// Adaptive layout container
@Composable
fun AdaptiveLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val isLargeScreen = rememberIsLargeScreen()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = if (isLargeScreen) 96.dp else 16.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

// User authentication button
@Composable
fun AuthButton(
    onClick: () -> Unit,
    text: String,
    maxWidth: Dp = 400.dp,
    widthFraction: Float = if (rememberIsLargeScreen()) 0.5f else 0.7f,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .widthIn(max = maxWidth)
            .fillMaxWidth(widthFraction)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = text,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(8.dp))
        }
        Text(text, fontSize = 16.sp)
    }
}

// Circular user initials display
@Composable
private fun UserInitials(
    name: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Text(
            name.take(1),
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold
        )
    }
}

// Account avatar component
@Composable
private fun AccountAvatar(
    label: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            label,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

// Remove account button
@Composable
private fun RemoveAccountButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Text("Remove")
    }
}

// Responsive button container
@Composable
fun ResponsiveButtonContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val isLargeScreen = rememberIsLargeScreen()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = if (isLargeScreen) 96.dp else 0.dp),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

// Login prompt content
@Composable
fun LoginPromptContent(onLogin: () -> Unit) {
    val loginProviders = buildList {
        add(
            AuthProvider(
                name = "Google",
                iconRes = Res.drawable.ic_google,
                backgroundColor = Color.White,
                textColor = Color.Black,
                onClick = onLogin
            )
        )

        if (PlatformConfig.isIos()) {
            add(
                AuthProvider(
                    name = "Apple",
                    iconRes = Res.drawable.ic_apple,
                    backgroundColor = Color.Black,
                    iconPadding = PaddingValues(horizontal = 12.dp, vertical = 3.dp),
                    textColor = Color.White,
                    onClick = onLogin
                )
            )
        }
        if (PlatformConfig.isIos() || PlatformConfig.isAndroid()) {

            add(
                AuthProvider(
                    name = "Passkey",
                    iconRes = Res.drawable.ic_pass_key,
                    backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                    textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    onClick = onLogin
                )
            )


       }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main title with emphasized typography
        Text(
            text = "Access Your Data\nEverywhere",
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 48.dp)
        )

        // Subtitle with clean spacing
        Text(
            text = "Login to sync your data across all your devices",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 16.dp, bottom = 40.dp)
        )

        // Authentication providers section
        Text(
            text = "Sign in with",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Auth providers in a clean column
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            loginProviders.forEach { provider ->
                AuthProviderButton(
                    provider = provider,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        CloudSyncAnimation(modifier = Modifier.size(240.dp))

        Text(
            "Sync Your Journey",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}


