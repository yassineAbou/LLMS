package org.yassineabou.llms.feature.you

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_apple
import llms.composeapp.generated.resources.ic_google
import llms.composeapp.generated.resources.ic_pass_key
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginAuthentification() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title with bottom padding
        LoginTitle(Modifier.padding(bottom = 32.dp))

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
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Third-party login options
        ThirdPartyLoginOptions(
            onPasskeyClick = { /* Handle passkey login */ },
            onGoogleClick = { /* Handle Google login */ },
            onAppleClick = { /* Handle Apple login */ }
        )
    }
}

@Composable
private fun LoginTitle(modifier: Modifier = Modifier) {
    Text(
        text = "Log in",
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier
    )
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
        modifier = modifier
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
        Text(
            text = " Register now ",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier.clickable { /* Handle registration */ }
        )
    }
}

@Composable
private fun ThirdPartyLoginOptions(
    onPasskeyClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onAppleClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Log in using:",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // Use FlowRow for responsive wrapping
        FlowRow(
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
            maxItemsInEachRow = 3
        ) {
            ProviderButton(
                text = "Passkey",
                icon = painterResource(Res.drawable.ic_pass_key),
                backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                onClick = onPasskeyClick,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
            ProviderButton(
                text = "Google",
                icon = painterResource(Res.drawable.ic_google),
                backgroundColor = Color.White,
                textColor = Color.Black,
                onClick = onGoogleClick,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
            ProviderButton(
                text = "Apple",
                icon = painterResource(Res.drawable.ic_apple),
                backgroundColor = Color.Black,
                textColor = Color.LightGray,
                onClick = onAppleClick,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun ProviderButton(
    text: String,
    icon: Painter,
    backgroundColor: Color,
    textColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledTonalButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = modifier
            .height(40.dp)
            .sizeIn(minWidth = 120.dp) // Set minimum width for better touch targets
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                color = textColor
            )
        }
    }
}