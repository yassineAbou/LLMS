package org.yassineabou.llms.feature.you.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_apple
import llms.composeapp.generated.resources.ic_google
import llms.composeapp.generated.resources.ic_pass_key
import org.yassineabou.llms.feature.you.view.AuthProvider
import org.yassineabou.llms.feature.you.view.AuthTopBar
import org.yassineabou.llms.feature.you.view.DividerWithText
import org.yassineabou.llms.feature.you.view.ThirdPartyAuthOptions

// Registration Screen with extracted field components
@Composable
fun RegistrationAuth(
    onRegister: () -> Unit,
    onDismiss: () -> Unit,
    onBackPressed: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val registrationProviders = listOf(
        AuthProvider(
            name = "Google",
            iconRes = Res.drawable.ic_google,
            backgroundColor = Color.White,
            textColor = Color.Black,
            onClick = { /* Handle Google login */ }
        ),
        AuthProvider(
            name = "Apple",
            iconRes = Res.drawable.ic_apple,
            backgroundColor = Color.Black,
            textColor = Color.LightGray,
            onClick = { /* Handle Apple login */ }
        ),
        AuthProvider(
            name = "Passkey",
            iconRes = Res.drawable.ic_pass_key,
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            textColor = MaterialTheme.colorScheme.onTertiaryContainer,
            onClick = { /* Handle passkey login */ }
        )
    )

    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        AuthTopBar(
            title = "Register",
            onBackPressed = onBackPressed,
            onDismissed = onDismiss
        )

        // Username field
        UsernameField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Email field
        EmailField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Password field
        PasswordField(
            value = password,
            onValueChange = { password = it },
            showPassword = showPassword,
            onShowPasswordToggle = { showPassword = !showPassword },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Register button
        Button(
            onClick = onRegister,
            modifier = Modifier
                .widthIn(min = 200.dp)
                .height(50.dp)
        ) {
            Text("Register", style = MaterialTheme.typography.labelLarge)
        }

        // Divider with "or"
        DividerWithText(
            text = "or",
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Third-party registration options
        ThirdPartyAuthOptions(
            title = "Register faster using:",
            providers = registrationProviders,
        )

    }
}

// Extracted field components
@Composable
fun UsernameField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Username: Required",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text("Enter username") }
        )
        Text(
            text = "This is the name that will be shown with your messages. You may use any name you wish.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun EmailField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Email: Required",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            placeholder = { Text("Enter email address") }
        )
    }
}

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    showPassword: Boolean,
    onShowPasswordToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Password: Required",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                visualTransformation = if (showPassword) VisualTransformation.None
                else PasswordVisualTransformation(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                placeholder = { Text("Enter password") }
            )
            IconButton(
                onClick = onShowPasswordToggle
            ) {
                Icon(
                    imageVector = if (showPassword) Icons.Outlined.VisibilityOff
                    else Icons.Outlined.Visibility,
                    contentDescription = "Toggle password visibility"
                )
            }
        }
        Text(
            text = "Entering a password is required",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}