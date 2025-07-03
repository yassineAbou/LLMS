package org.yassineabou.llms.feature.you

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
import org.yassineabou.llms.feature.imagine.ui.util.rememberIsLargeScreen


// State container that manages authentication state
@Composable
fun YouScreen() {
    var isLoggedIn by remember { mutableStateOf(false) }
    var showLogoutPrompt by remember { mutableStateOf(false) }
    var showAuthentification by remember { mutableStateOf(false) }

    YouContent(
        isLoggedIn = isLoggedIn,
        onLogin = {
            showAuthentification = true
            //isLoggedIn = true
            //showLogoutPrompt = false
        },
        onLogout = {
            //isLoggedIn = false
            //showLogoutPrompt = true
        },
        showLogoutPrompt = showLogoutPrompt
    )

    if (showAuthentification) {
        AuthentificationBottomSheet(
            onDismiss = { showAuthentification = false },
        )
    }
}

// Main screen composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YouContent(
    isLoggedIn: Boolean,
    onLogin: () -> Unit,
    onLogout: () -> Unit,
    showLogoutPrompt: Boolean
) {
    val isLargeScreen = rememberIsLargeScreen()
    val horizontalPadding = if (isLargeScreen) 48.dp else 16.dp

    Scaffold(
        topBar = { AuthTopBar(isLoggedIn) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = horizontalPadding, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile or login content
            if (isLoggedIn) ProfileContent(onLogout) else LoginPromptContent(onLogin)

            Spacer(Modifier.weight(1f))

            // Animation section
            AuthStatusAnimationSection(isLoggedIn)

            Spacer(Modifier.weight(1f))

            // Logout confirmation
            if (showLogoutPrompt) LogoutConfirmation()
        }
    }
}

// Top bar with dynamic title
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AuthTopBar(isLoggedIn: Boolean) {
    TopAppBar(
        title = {
            Text(
                text = if (isLoggedIn) "Profile" else "Login",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    )
}

// Section showing authentication status animation
@Composable
private fun AuthStatusAnimationSection(isLoggedIn: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoggedIn) {
            VerifiedUserAnimation(modifier = Modifier.size(200.dp))
            Spacer(Modifier.height(24.dp))
            Text(
                "Account Verified",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                "Your data is securely synced across all devices",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        } else {
            CloudSyncAnimation(modifier = Modifier.size(240.dp))
            Spacer(Modifier.height(24.dp))
            Text(
                "Sync Your Journey",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                "Login to access your data from any device",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

// Logout confirmation message
@Composable
private fun LogoutConfirmation() {
    Spacer(Modifier.height(24.dp))
    Text(
        "You have been logged out",
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

// Responsive button container
@Composable
fun ResponsiveButtonContainer(
    content: @Composable () -> Unit
) {
    val isLargeScreen = rememberIsLargeScreen()
    Box(
        modifier = Modifier
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
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(48.dp))
        Text(
            "Access Your Data Everywhere",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(16.dp))
        Text(
            "Login to sync your data across all your devices",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(32.dp))

        ResponsiveButtonContainer {
            AuthButton(
                onClick = onLogin,
                text = "Sign In",
                maxWidth = 400.dp,
                widthFraction = if (rememberIsLargeScreen()) 0.5f else 0.7f,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

// Profile content
@Composable
fun ProfileContent(onLogout: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        ProfileHeader()
        Spacer(Modifier.height(24.dp))

        ResponsiveButtonContainer {
            AuthButton(
                onClick = onLogout,
                text = "Log out",
                icon = Icons.AutoMirrored.Filled.ExitToApp,
                maxWidth = 400.dp,
                widthFraction = if (rememberIsLargeScreen()) 0.6f else 1f,
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(Modifier.height(32.dp))
        GoogleAccountCard(onLogout)
    }
}

// Profile header section
@Composable
private fun ProfileHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "P",
                fontSize = 32.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.width(16.dp))

        Column {
            Text("Pet Companion", style = MaterialTheme.typography.titleLarge)
            Text(
                "@pet_friend",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 16.sp
            )
        }
    }
}

// Google account card
@Composable
private fun GoogleAccountCard(onLogout: () -> Unit) {
    ResponsiveButtonContainer {
        Card(
            modifier = Modifier
                .widthIn(max = 600.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "G",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }

                Spacer(Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text("Google Account", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "petfriend@gmail.com",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Button(
                    onClick = onLogout,
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
        }
    }
}

// Reusable authentication button
@Composable
fun AuthButton(
    onClick: () -> Unit,
    text: String,
    maxWidth: Dp,
    widthFraction: Float,
    containerColor: Color,
    contentColor: Color,
    icon: ImageVector? = null
) {
    Button(
        onClick = onClick,
        modifier = Modifier
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
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(8.dp))
        }
        Text(text, fontSize = 16.sp)
    }
}






