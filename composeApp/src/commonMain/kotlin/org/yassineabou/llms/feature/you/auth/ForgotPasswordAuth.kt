package org.yassineabou.llms.feature.you.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.yassineabou.llms.feature.you.view.AuthTopBar

@Composable
fun ForgotPasswordAuth(
    onReset: () -> Unit,
    onDismiss: () -> Unit
) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // Center all children horizontally
    ) {
        AuthTopBar(
            title = "Lost password",
            onBackPressed = onReset,
            onDismissed = onDismiss
        )

        // Description - left aligned
        Text(
            text = "If you have forgotten your password, you can use this form to reset your password. You will receive an email with instructions.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth() // Take full width
                .padding(bottom = 32.dp),
            textAlign = TextAlign.Start // Left align text
        )

        // Email section (header, description, and input field)
        EmailResetField(
            email = email,
            onEmailChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        )

        // Reset button - centered
        Button(
            onClick = onReset,
            modifier = Modifier
                .widthIn(min = 200.dp) // Minimum width for better appearance
                .height(50.dp)
        ) {
            Text("Reset", style = MaterialTheme.typography.labelLarge)
        }
    }
}

// Reusable email section component
@Composable
fun EmailResetField(
    email: String,
    onEmailChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Email section header
        Text(
            text = "Email:",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp),
            textAlign = TextAlign.Start
        )

        // Email description
        Text(
            text = "The email address you are registered with is required to reset your password.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Start
        )

        // Email input field
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
            placeholder = { Text("Enter your email address") }
        )
    }
}