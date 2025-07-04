package org.yassineabou.llms.feature.you

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_apple
import llms.composeapp.generated.resources.ic_google
import llms.composeapp.generated.resources.ic_pass_key

// Login Screen
@Composable
fun LoginAuthentification() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val loginProviders = listOf(
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
        // Title with bottom padding
        Text(
            text = "Log in",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 32.dp)
        )


        // Email field with bottom padding
        EmailInputField(
            email = email,
            onEmailChange = { email = it },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Password field with bottom padding
        PasswordInputField(
            password = password,
            onPasswordChange = { password = it },
            showPassword = showPassword,
            onShowPasswordToggle = { showPassword = !showPassword },
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // Forgot password link
        ForgotPasswordLink(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
        )

        // Login button with bottom padding
        LoginButton(
            onClick = { /* Handle login */ },
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Registration link with bottom padding
        RegistrationLink(
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Add divider with "or" text
        DividerWithText(
            text = "or",
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Third-party login options
        ThirdPartyAuthOptions(
            title = "Log in using:",
            providers = loginProviders
        )
    }
}
@Composable
private fun EmailInputField(
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Your name or email address") },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
    )
}

@Composable
private fun PasswordInputField(
    password: String,
    onPasswordChange: (String) -> Unit,
    showPassword: Boolean,
    onShowPasswordToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Password:",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                modifier = Modifier.weight(1f),
                visualTransformation = if (showPassword) VisualTransformation.None
                else PasswordVisualTransformation(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )

            IconButton(
                onClick = onShowPasswordToggle,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                val icon = if (showPassword) Icons.Outlined.VisibilityOff
                else Icons.Outlined.Visibility
                Icon(
                    imageVector = icon,
                    contentDescription = "Toggle password visibility"
                )
            }
        }
    }
}

@Composable
private fun ForgotPasswordLink(modifier: Modifier = Modifier) {
    Text(
        text = " Forgot your password? ",
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.primary,
            textDecoration = TextDecoration.Underline
        ),
        modifier = modifier
            .clickable { /* Handle forgot password */ }
    )
}

@Composable
private fun LoginButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Lock",
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text("Log In")
        }
    }
}

@Composable
private fun RegistrationLink(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Don't have an account? ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Button(
            onClick = { /* Handle registration */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = "Register now",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }
}