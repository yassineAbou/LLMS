package org.yassineabou.llms.feature.you.ui.util

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.NoCredentialException
import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import org.yassineabou.llms.MyApp
import org.yassineabou.llms.feature.you.PasskeyFunctions

actual object PasskeyAuth {

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    actual suspend fun signInWithPasskey(
        onSuccess: (String) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val passkeyFunctions = PasskeyFunctions()

        val requestJson = """
            {
              "challenge": "T1xCsnxM2DNL2KdK5CLa6fMhD7OBqho6syzInk_n-Uo",
              "allowCredentials": [],
              "timeout": 1800000,
              "userVerification": "required",
              "rpId": "your-rp-id.example.com"
            }
        """.trimIndent()

        passkeyFunctions.signInFlow(
            requestJson = requestJson,
            onSuccess = { credentialResponse ->
                val context = MyApp.getContext()

                // Extract authentication data from the response
                val authData = when (val credential = credentialResponse.credential) {
                    is PublicKeyCredential -> {
                        //œÂLogger.d { "Passkey authentication successful" }
                        credential.authenticationResponseJson
                    }
                    is PasswordCredential -> {
                        //Logger.d { "Password authentication successful" }
                        // Create a JSON response for password auth
                        JSONObject().apply {
                            put("type", "password")
                            put("id", credential.id)
                            put("userHandle", credential.id)
                        }.toString()
                    }
                    else -> {
                        //Logger.w { "Unknown credential type" }
                        "{}"
                    }
                }

                Toast.makeText(context, "Signed in with passkey!", Toast.LENGTH_SHORT).show()
                onSuccess(authData)
            },
            onFailure = { e ->
                if (e is NoCredentialException) {
                    CoroutineScope(Dispatchers.Main).launch {
                        createPasskey(onSuccess = {
                            // After creating passkey, sign in again
                            launch {  signInWithPasskey(onSuccess, onFailure) }
                        }, onFailure)
                    }
                } else {
                    val context = MyApp.getContext()
                    Toast.makeText(context, "Sign-in failed: ${e.message}", Toast.LENGTH_SHORT).show()
                    onFailure(e)
                }
            }
        )
    }

   suspend fun createPasskey(onSuccess: () -> Unit, onFailure: (Throwable) -> Unit) {
        // Create instance dynamically (avoids static context leak)
        val passkeyFunctions = PasskeyFunctions()

        // Sample creation JSON (customize with user details, challenge, etc.)
        val requestJson = """
            {
              "challenge": "abc123",
              "rp": {"name": "Your App", "id": "your-rp-id.example.com"},
              "user": {"id": "user-id", "name": "user@example.com", "displayName": "User"},
              "pubKeyCredParams": [{"type": "public-key", "alg": -7}, {"type": "public-key", "alg": -257}],
              "timeout": 1800000,
              "attestation": "none",
              "authenticatorSelection": {"userVerification": "required"}
            }
        """.trimIndent()

        passkeyFunctions.createPasskey(
            requestJson = requestJson,
            onSuccess = {
                val  context = MyApp.Companion.getContext()
                Toast.makeText(context, "Passkey created! Now signing in.", Toast.LENGTH_SHORT).show()
                onSuccess()
            },
            onFailure = { e ->
                val context = MyApp.Companion.getContext()
                Toast.makeText(context, "Passkey creation failed: ${e.message}", Toast.LENGTH_SHORT).show()
                onFailure(e)
            }
        )
    }
}