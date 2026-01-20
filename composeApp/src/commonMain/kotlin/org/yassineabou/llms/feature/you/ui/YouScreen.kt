package org.yassineabou.llms.feature.you.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.panpf.sketch.AsyncImage
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_google
import org.yassineabou.llms.app.core.sharedViews.SnackbarController
import org.yassineabou.llms.app.core.sharedViews.ConfirmationDialogContent
import org.yassineabou.llms.feature.imagine.ui.util.rememberIsLargeScreen
import org.yassineabou.llms.feature.imagine.ui.view.DropDownDialog
import org.yassineabou.llms.feature.you.data.model.AuthInfo
import org.yassineabou.llms.feature.you.data.model.AuthState
import org.yassineabou.llms.feature.you.ui.view.AuthProvider
import org.yassineabou.llms.feature.you.ui.view.AuthProviderButton
import org.yassineabou.llms.feature.you.ui.view.AuthScreenTransition
import org.yassineabou.llms.feature.you.ui.view.CloudSyncAnimation
import org.yassineabou.llms.feature.you.ui.view.VerifiedUserAnimation


// State container that manages authentication state
@Composable
fun YouScreen(youViewModel: YouViewModel) {
    val isLoggedIn by youViewModel.isLoggedIn.collectAsStateWithLifecycle()
    val authInfo by youViewModel.authInfo.collectAsStateWithLifecycle()
    val authState by youViewModel.authState.collectAsStateWithLifecycle()
    val snackbarController = SnackbarController.current

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Error -> {
                snackbarController.showMessage(
                    message = (authState as AuthState.Error).message,
                    duration = SnackbarDuration.Long
                )
            }
            AuthState.Success -> {
                snackbarController.showMessage(
                    message = "Successfully logged in!",
                    duration = SnackbarDuration.Short
                )
            }
            else -> {}
        }
    }



    YouContent(
        isLoggedIn = isLoggedIn,
        authInfo = authInfo,
        onLogin = { youViewModel.onLogin()},
        onLogout = { youViewModel.onLogout() },
        onDeleteAccount = { youViewModel.onDeleteAccount() }
    )
}

// Main screen composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YouContent(
    isLoggedIn: Boolean,
    authInfo: AuthInfo?,
    onLogin: () -> Unit,
    onLogout: () -> Unit,
    onDeleteAccount: () -> Unit
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
            ProfileContent(
                authInfo = authInfo,
                onLogout = onLogout,
                onDeleteAccount = onDeleteAccount
            )
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
    authInfo: AuthInfo?,
    onLogout: () -> Unit,
    onDeleteAccount: () -> Unit
) {
    // Use authInfo for dynamic data; fallback to defaults
    val displayName = authInfo?.username ?: "User"

    // CHANGED: Display accurate information instead of a fake email
    val displayDetail = authInfo?.username ?: "user@example.com"
    val displayLabel = displayName.take(1).uppercase() // For avatar

    var showDeleteAccountDialog by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        ProfileHeader(
            name = displayName,
            imageUrl = authInfo?.imageUrl,
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
            label = displayLabel,
            email = displayDetail,
            imageUrl = authInfo?.imageUrl,
            onRemove = { showDeleteAccountDialog = true },
            modifier = Modifier.padding(top = 32.dp)
        )

        VerifiedUserAnimation(modifier = Modifier.size(200.dp))

        AuthenticationSuccessContent()
    }

    if (showDeleteAccountDialog) {
        DropDownDialog(
            onDismissRequest = { showDeleteAccountDialog = false }
        ) {
            ConfirmationDialogContent(
                title = "Delete Account?",
                message = "This will permanently delete your account and all your data including chats and images. This action cannot be undone.",
                icon = Icons.Filled.Delete,
                confirmText = "Delete",
                dismissText = "Cancel",
                onConfirm = {
                    showDeleteAccountDialog = false
                    onDeleteAccount()
                },
                onDismiss = { showDeleteAccountDialog = false }
            )
        }
    }
}

// User profile header
@Composable
private fun ProfileHeader(
    name: String,
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserInitials(
            name = name,
            imageUrl = imageUrl,
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
    imageUrl: String?,
    modifier: Modifier = Modifier,
    onRemove: () -> Unit,
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
                    imageUrl = imageUrl,
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

                RemoveAccountButton(onRemove)
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
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        if (!imageUrl.isNullOrEmpty()) {
            AsyncImage(
                uri = imageUrl,
                contentDescription = "Profile picture",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Text(
                name.take(1),
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// Account avatar component
@Composable
private fun AccountAvatar(
    label: String,
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        if (!imageUrl.isNullOrEmpty()) {
            AsyncImage(
                uri = imageUrl,
                contentDescription = "Profile picture",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Text(
                text = label,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
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
        AuthProviderButton(
            provider = AuthProvider(
                name = "Google",
                iconRes = Res.drawable.ic_google,
                backgroundColor = Color.White,
                textColor = Color.Black,
                onClick = { onLogin() }  // Directly trigger login
            ),
            modifier = Modifier.padding(8.dp)
        )

        CloudSyncAnimation(modifier = Modifier.size(240.dp))

        Text(
            "Sync Your Journey",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}


