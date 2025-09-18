@file:OptIn(ExperimentalTime::class)


package org.yassineabou.llms.repository

import kotlin.time.ExperimentalTime

/*
class ImageRepository {

    suspend fun createImage(userId: String, image: GeneratedImageEntity) = dbQuery {
        // Verify the image's userId matches the authenticated user
        if (image.userId != userId) {
            throw SecurityException("User ID mismatch")
        }

        GeneratedImagesTable.insert {
            it[id] = image.id
            it[GeneratedImagesTable.userId] = image.userId
            it[prompt] = image.prompt
            it[imageData] = image.imageData
            it[imageModelName] = image.imageModelName
            it[generatedAt] = image.generatedAt
        }.let { image }
    }

    suspend fun getImagesForUser(userId: String) = dbQuery {
        GeneratedImagesTable.select(GeneratedImagesTable.userId eq userId )
            .orderBy(GeneratedImagesTable.generatedAt, SortOrder.DESC)
            .map { toGeneratedImage(it) }
    }

    suspend fun deleteImage(userId: String, imageId: String): Int = dbQuery {
        GeneratedImagesTable.deleteWhere {
            (id eq imageId) and (GeneratedImagesTable.userId eq userId)
        }
    }

    suspend fun clearAllImagesForUser(userId: String): Int = dbQuery {
        GeneratedImagesTable.deleteWhere { GeneratedImagesTable.userId eq userId }
    }


    private fun toGeneratedImage(row: ResultRow): GeneratedImageEntity = GeneratedImageEntity(
        id = row[GeneratedImagesTable.id],
        userId = row[GeneratedImagesTable.userId],
        prompt = row[GeneratedImagesTable.prompt],
        imageData = row[GeneratedImagesTable.imageData],
        imageModelName = row[GeneratedImagesTable.imageModelName],
        generatedAt = row[GeneratedImagesTable.generatedAt]
    )
}

 */