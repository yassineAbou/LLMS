package org.yassineabou.llms.feature.you

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import org.yassineabou.llms.feature.imagine.ui.util.rememberIsLargeScreen
import org.yassineabou.llms.feature.you.view.AuthScreenTransition
import org.yassineabou.llms.feature.you.view.CloudSyncAnimation
import org.yassineabou.llms.feature.you.view.VerifiedUserAnimation


// State container that manages authentication state
@Composable
fun YouScreen(youViewModel: YouViewModel) {
    val isLoggedIn by youViewModel.isLoggedIn.collectAsStateWithLifecycle()
    val showAuthSheet by youViewModel.showAuthSheet.collectAsStateWithLifecycle()
    val currentAuthScreen by youViewModel.authScreen.collectAsStateWithLifecycle()

    YouContent(
        isLoggedIn = isLoggedIn,
        onLogin = {
            youViewModel.onShowAuthSheet(true)
            youViewModel.resetAuthScreen()
        },
        onLogout = { youViewModel.onLogout() }
    )

    if (showAuthSheet) {
        AuthBottomSheet(
            currentAuthScreen = currentAuthScreen,
            youViewModel = youViewModel,
        )
    }
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
fun ProfileContent(onLogout: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        ProfileHeader(modifier = Modifier.padding(top = 24.dp))

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
            onLogout = onLogout,
            modifier = Modifier.padding(top = 32.dp)
        )

        VerifiedUserAnimation(modifier = Modifier.size(200.dp))
        AuthenticationSuccessContent()


    }
}

// User profile header
@Composable
private fun ProfileHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserInitials(
            name = "Pet Companion",
            modifier = Modifier.size(72.dp)
        )

        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text("Pet Companion", style = MaterialTheme.typography.titleLarge)
            Text(
                "@pet_friend",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 16.sp
            )
        }
    }
}

// Google account integration card
@Composable
private fun AccountCard(
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
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
                    label = "G",
                    modifier = Modifier.size(40.dp)
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                ) {
                    Text("Google Account", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "petfriend@gmail.com",
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

// Sync prompt content
@Composable
private fun AuthenticationPromptContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text(
            "Sync Your Journey",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            "Login to access your data from any device",
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Access Your Data Everywhere",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 48.dp)
        )
        Text(
            "Login to sync your data across all your devices",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 16.dp)
        )

        ResponsiveButtonContainer(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            AuthButton(
                onClick = onLogin,
                text = "Sign In",
                maxWidth = 400.dp,
                widthFraction = if (rememberIsLargeScreen()) 0.5f else 0.7f,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }

        CloudSyncAnimation(modifier = Modifier.size(240.dp))
        AuthenticationPromptContent()
    }
}


