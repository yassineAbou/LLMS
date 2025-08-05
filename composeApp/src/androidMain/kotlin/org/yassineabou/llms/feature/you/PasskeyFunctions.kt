package org.yassineabou.llms.feature.you

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.credentials.*
import androidx.credentials.exceptions.*
import androidx.credentials.exceptions.publickeycredential.CreatePublicKeyCredentialDomException
import kotlinx.coroutines.coroutineScope
import org.yassineabou.llms.MyApp

// Provided class (unchanged; integrated below)
// Updated with Kermit logging (replaced all Log calls)
class PasskeyFunctions() {
    val context: Context = MyApp.getContext() // Assuming this is Application context (safe for static use)
    private val credentialManager = CredentialManager.create(context)
    private val TAG = "PasskeyAuth" // For Kermit tags (optional)

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    suspend fun signInFlow(
        requestJson: String,
        onSuccess: (GetCredentialResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val getPasswordOption = GetPasswordOption()
        val getPublicKeyCredentialOption = GetPublicKeyCredentialOption(requestJson = requestJson)

        val credentialRequest = GetCredentialRequest(
            listOf(getPasswordOption, getPublicKeyCredentialOption)
        )

        coroutineScope {
            try {
                // Optional: Prepare for lower latency on Android 14+
                credentialManager.prepareGetCredential(
                    GetCredentialRequest(
                        listOf(getPublicKeyCredentialOption, getPasswordOption)
                    )
                )

                val result = credentialManager.getCredential(
                    context = context,
                    request = credentialRequest
                )
                handleSignIn(result)
                onSuccess(result)
            } catch (e: GetCredentialException) {
                //Logger.e(TAG, throwable = e) { "Sign-in failed" }
                onFailure(e)
            }
        }
    }

    suspend fun createPasskey(
        requestJson: String,
        preferImmediatelyAvailableCredentials: Boolean = false,
        onSuccess: () -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val createPublicKeyCredentialRequest = CreatePublicKeyCredentialRequest(
            requestJson = requestJson,
            preferImmediatelyAvailableCredentials = preferImmediatelyAvailableCredentials
        )

        coroutineScope {
            try {
                val result = credentialManager.createCredential(
                    context = context,
                    request = createPublicKeyCredentialRequest
                )
                // Handle result (e.g., send to server)
                //Logger.d(TAG) { "Passkey created successfully" }
                onSuccess()
            } catch (e: CreateCredentialException) {
                handleFailure(e)
                onFailure(e)
            }
        }
    }

    private fun handleSignIn(result: GetCredentialResponse) {
        val credential = result.credential

        when (credential) {
            is PublicKeyCredential -> {
                val responseJson = credential.authenticationResponseJson
                // TODO: Send responseJson to your server for validation and authentication
                //Logger.d(TAG) { "Passkey sign-in successful: $responseJson" }
            }
            is PasswordCredential -> {
                val username = credential.id
                val password = credential.password
                // TODO: Send to server for validation
                //Logger.d(TAG) { "Password sign-in: $username" }
            }
            is CustomCredential -> {
                if (credential.type == ExampleCustomCredential.TYPE) {
                    try {
                        val exampleCustomCredential = ExampleCustomCredential.createFrom(credential.data)
                        // TODO: Complete authentication flow
                    } catch (e: ExampleCustomCredential.ExampleCustomCredentialParsingException) {
                        //Logger.e(TAG, throwable = e) { "Failed to parse custom credential" }
                    }
                } else {
                    //Logger.e(TAG) { "Unexpected custom credential type" }
                }
            }
            else -> {
               // Logger.e(TAG) { "Unexpected credential type" }
            }
        }
    }

    private fun handleFailure(e: CreateCredentialException) {
        when (e) {
            is CreatePublicKeyCredentialDomException -> {
                // Handle WebAuthn DOM errors
                //Logger.e(TAG, throwable = e) { "DOM error during passkey creation" }
            }
            is CreateCredentialCancellationException -> {
                //Logger.d(TAG) { "User canceled passkey creation" }
            }
            is CreateCredentialInterruptedException -> {
                //Logger.w(TAG, throwable = e) { "Interrupted; retry possible" }
            }
            is CreateCredentialProviderConfigurationException -> {
                //Logger.e(TAG, throwable = e) { "Missing provider config (check dependencies)" }
            }
            is CreateCredentialCustomException -> {
                //Logger.e(TAG, throwable = e) { "Custom SDK error" }
            }
            else -> ""
        }
    }

    fun handleFailure(e: GetCredentialException) {
        //Logger.e(TAG, throwable = e) { "GetCredential failed" }
    }

    // Other methods (e.g., registerPassword, autofillImplementation) omitted for now; add if needed
}

// ExampleCustomCredential as provided (placeholder; unchanged)
sealed class ExampleCustomCredential {
    class ExampleCustomCredentialParsingException : Throwable() {}

    companion object {
        fun createFrom(data: Bundle): PublicKeyCredential {
            return PublicKeyCredential("") // Placeholder; implement as needed
        }

        const val TYPE: String = "" // Placeholder; set to your custom type
    }
}
