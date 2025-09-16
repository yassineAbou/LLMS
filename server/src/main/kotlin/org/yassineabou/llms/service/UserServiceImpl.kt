@file:OptIn(ExperimentalTime::class)


package org.yassineabou.llms.service

import kotlin.time.ExperimentalTime

// server/src/main/kotlin/UserServiceImpl.kt
/*
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override suspend fun findByIdOrCreateUser(
        id: String,
        username: String,
        email: String,
        profilePicUrl: String?
    ): UserDto {
        val user = userRepository.findByIdOrCreateUser(id, username, email, profilePicUrl)
        return UserDto(
            id = user.id,
            username = user.username,
            email = user.email,
            profilePicUrl = user.profilePicUrl,
            createdAt = user.createdAt.toString() // Convert Instant to String
        )
    }

    override suspend fun deleteUser(userId: String): Int {
        return userRepository.deleteUser(userId)
    }
}

 */