@file:OptIn(ExperimentalTime::class)


package org.yassineabou.llms.service

import kotlin.time.ExperimentalTime

/**
 * The server-side implementation of the ImageService.
 * It uses the ImageRepository to interact with the database and maps
 * database entities to network-friendly DTOs.
 */
/*
class ImageServiceImpl(private val imageRepository: ImageRepository) : ImageService {

    override suspend fun createImage(
        id: String,
        userId: String,
        prompt: String,
        imageData: ByteArray,
        imageModelName: String
    ): GeneratedImageDto {
        // 1. Create a server-side Entity. The server is authoritative for the ID and timestamp.
        val newImageEntity = GeneratedImageEntity(
            id = id,
            userId = userId,
            prompt = prompt,
            imageData = imageData,
            imageModelName = imageModelName,
            generatedAt = Clock.System.now()
        )

        // 2. Call the repository to save the new image.
        val createdEntity = imageRepository.createImage(userId, newImageEntity)

        // 3. Convert the resulting entity to a DTO to send back to the client.
        return GeneratedImageDto(
            id = createdEntity.id,
            userId = createdEntity.userId,
            prompt = createdEntity.prompt,
            imageData = createdEntity.imageData,
            imageModelName = createdEntity.imageModelName,
            generatedAt = createdEntity.generatedAt.toString()
        )
    }

    override suspend fun getImagesForUser(userId: String): Flow<GeneratedImageDto> {
        // 1. Call the repository to get the list of image entities.
        val imageEntities = imageRepository.getImagesForUser(userId)

        // 2. Map the list of entities to a list of DTOs for the client.
        return imageEntities.map { entity ->
            GeneratedImageDto(
                id = entity.id,
                userId = entity.userId,
                prompt = entity.prompt,
                imageData = entity.imageData,
                imageModelName = entity.imageModelName,
                generatedAt = entity.generatedAt.toString()
            )
        }
    }

    override suspend fun deleteImage(userId: String, imageId: String): Int {
        // Direct pass-through. Repository handles security check.
        return imageRepository.deleteImage(userId, imageId)
    }

    override suspend fun clearAllImagesForUser(userId: String): Int {
        // Direct pass-through.
        return imageRepository.clearAllImagesForUser(userId)
    }
}

 */