package org.yassineabou.llms.api

// shared/src/commonMain/kotlin/UserService.kt
// DTO (Data Transfer Object)
/*
@Serializable
data class UserDto(
    val id: String,
    val username: String,
    val email: String,
    val profilePicUrl: String?,
    val createdAt: String // Using String for serialization compatibility
)

/*
@Rpc
interface UserService {
    suspend fun findByIdOrCreateUser(
        id: String,
        username: String,
        email: String,
        profilePicUrl: String?
    ): UserDto

    suspend fun deleteUser(userId: String): Int
}

 */