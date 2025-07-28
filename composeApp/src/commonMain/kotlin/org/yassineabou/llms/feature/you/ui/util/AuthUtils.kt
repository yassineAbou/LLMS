package org.yassineabou.llms.feature.you.ui.util

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.yassineabou.llms.feature.you.model.AuthInfo
import org.yassineabou.llms.feature.you.model.AuthMethod

object AuthUtils {

    /**
     * Parses the passkey authentication data and returns an AuthInfo object.
     * In a real app, this would be replaced by a backend call.
     */
    fun parsePasskeyAuthData(authData: String): AuthInfo {
        return try {
            val json = Json.parseToJsonElement(authData).jsonObject
            val response = json["response"]?.jsonObject

            val userHandle = response?.get("userHandle")?.jsonPrimitive?.content
                ?: json["id"]?.jsonPrimitive?.content
                ?: "unknown_passkey_user"

            val credentialId = json["id"]?.jsonPrimitive?.content ?: "unknown_credential"
            val uniqueSuffix = credentialId.takeLast(7)
            val displayName = "Passkey User (...$uniqueSuffix)"

            AuthInfo(
                userId = userHandle,
                username = displayName,
                authMethod = AuthMethod.PASSKEY,
                credentialId = credentialId,
                authenticatorData = response?.get("authenticatorData")?.jsonPrimitive?.content,
                clientDataJSON = response?.get("clientDataJSON")?.jsonPrimitive?.content,
                publicKey = json["rawId"]?.jsonPrimitive?.content
            )
        } catch (e: Exception) {
            AuthInfo(
                userId = "parse_error",
                username = "Passkey User (Error)",
                authMethod = AuthMethod.PASSKEY
            )
        }
    }
}