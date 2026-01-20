@file:OptIn(ExperimentalTime::class)


package org.yassineabou.llms.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.r2dbc.deleteWhere
import org.jetbrains.exposed.v1.r2dbc.insert
import org.jetbrains.exposed.v1.r2dbc.select
import org.jetbrains.exposed.v1.r2dbc.selectAll
import org.jetbrains.exposed.v1.r2dbc.upsert
import org.yassineabou.llms.database.DatabaseFactory.dbQuery
import org.yassineabou.llms.database.DatabaseFactory.dbQueryAsFlow
import org.yassineabou.llms.database.tables.GeneratedImageEntity
import org.yassineabou.llms.database.tables.GeneratedImagesTable
import kotlin.time.ExperimentalTime


class ImageRepository {

    suspend fun createImage(userId: String, image: GeneratedImageEntity) = dbQuery {
        // Verify the image's userId matches the authenticated user
        if (image.userId != userId) {
            throw SecurityException("User ID mismatch")
        }

        GeneratedImagesTable.upsert {
            it[id] = image.id
            it[GeneratedImagesTable.userId] = image.userId
            it[prompt] = image.prompt
            it[imageData] = image.imageData
            it[imageModelName] = image.imageModelName
            it[generatedAt] = image.generatedAt
        }.let { image }
    }

    fun getImagesForUser(userId: String): Flow<GeneratedImageEntity> = dbQueryAsFlow {
        // Use selectAll().where { } instead of select()
        GeneratedImagesTable
            .selectAll()
            .where { GeneratedImagesTable.userId eq userId }
            .orderBy(GeneratedImagesTable.generatedAt, SortOrder.DESC)
            .map { toGeneratedImage(it) }
            .toList()
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
