package org.yassineabou.llms.feature.you.ui.util


import platform.AuthenticationServices.*
import platform.Foundation.*
import platform.UIKit.UIApplication
import platform.UIKit.UIWindow
import platform.darwin.NSObject
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

actual object PasskeyAuth {

    actual suspend fun signInWithPasskey(
        onSuccess: (String) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        try {
            // 1. Get challenge from your server (simulate for now)
            val challengeString = "random-challenge-from-server"
            val challenge = (challengeString as NSString).dataUsingEncoding(NSUTF8StringEncoding) ?: NSData()

            // 2. Create the provider and request
            val provider = ASAuthorizationPlatformPublicKeyCredentialProvider("example.com")
            val request = provider.createCredentialAssertionRequestWithChallenge(challenge)

            // 3. Create the controller
            val controller = ASAuthorizationController(listOf(request))

            // 4. Set up delegate
            val delegate = PasskeyAuthDelegate(onSuccess, onFailure)
            controller.delegate = delegate
            controller.presentationContextProvider = delegate

            // 5. Perform the request on main thread
            dispatch_async(dispatch_get_main_queue()) {
                controller.performRequests()
            }
        } catch (e: Throwable) {
            onFailure(e)
        }
    }
}

// Helper delegate class
class PasskeyAuthDelegate(
    private val onSuccess: (String) -> Unit,
    private val onFailure: (Throwable) -> Unit
) : NSObject(), ASAuthorizationControllerDelegateProtocol, ASAuthorizationControllerPresentationContextProvidingProtocol {

    override fun presentationAnchorForAuthorizationController(controller: ASAuthorizationController): ASPresentationAnchor {
        // Return the current UIWindow
        return UIApplication.sharedApplication.keyWindow ?: UIWindow()
    }

    override fun authorizationController(controller: ASAuthorizationController, didCompleteWithAuthorization: ASAuthorization) {
        val credential = didCompleteWithAuthorization.credential
        when (credential) {
            is ASAuthorizationPlatformPublicKeyCredentialAssertion -> {
                // Extract credential data using correct property names
                val rawClientDataJSON = credential.rawClientDataJSON
                val rawAuthenticatorData = credential.rawAuthenticatorData
                val signature = credential.signature
                val userID = credential.userID
                val credentialID = credential.credentialID

                // Convert to base64 strings with correct ULong parameter
                val clientDataJSON = rawClientDataJSON.base64EncodedStringWithOptions(0u)
                val authenticatorData = rawAuthenticatorData?.base64EncodedStringWithOptions(0u)
                val signatureString = signature?.base64EncodedStringWithOptions(0u)
                val userIDString = userID?.base64EncodedStringWithOptions(0u)
                val credentialIdString = credentialID.base64EncodedStringWithOptions(0u)

                // Serialize as JSON
                val authData = """
                    {
                        "clientDataJSON": "$clientDataJSON",
                        "authenticatorData": "$authenticatorData",
                        "signature": "$signatureString",
                        "userID": "$userIDString",
                        "credentialId": "$credentialIdString"
                    }
                """.trimIndent()
                onSuccess(authData)
            }
            else -> {
                onFailure(Throwable("Unknown credential type"))
            }
        }
    }

    override fun authorizationController(controller: ASAuthorizationController, didCompleteWithError: NSError) {
        val errorMessage = when (didCompleteWithError.code) {
            ASAuthorizationErrorCanceled -> "Authentication canceled by user"
            ASAuthorizationErrorFailed -> "Authentication failed"
            ASAuthorizationErrorInvalidResponse -> "Invalid response from authenticator"
            ASAuthorizationErrorNotHandled -> "Authentication request not handled"
            ASAuthorizationErrorUnknown -> "Unknown authentication error"
            else -> didCompleteWithError.localizedDescription
        }
        onFailure(Throwable(errorMessage))
    }
}