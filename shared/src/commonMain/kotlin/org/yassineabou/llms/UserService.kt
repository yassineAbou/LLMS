package org.yassineabou.llms

import kotlinx.coroutines.flow.Flow
import kotlinx.rpc.annotations.Rpc
import kotlinx.serialization.Serializable

@Serializable
data class UserData(val address: String, val lastName: String)

@Rpc
interface UserService {
    suspend fun hello(platform: String, userData: UserData): String

    fun subscribeToNews(): Flow<String>

    fun subscribeToTopic(topic: String): Flow<String>

    suspend fun ping(): String
}