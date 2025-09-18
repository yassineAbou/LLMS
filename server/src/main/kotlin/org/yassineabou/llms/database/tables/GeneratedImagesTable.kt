@file:OptIn(ExperimentalTime::class)


package org.yassineabou.llms.database.tables

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.timestamp
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/*
object GeneratedImagesTable : Table("generated_images") {
    val id = text("id")
    val userId = text("user_id").references(
        UsersTable.id,
        onDelete = ReferenceOption.CASCADE
    ).index() // Index for filtering by user
    val prompt = text("prompt")
    val imageData = binary("image_data") // BYTEA in PostgreSQL
    val imageModelName = text("image_model_name")
    val generatedAt = timestamp("created_at").default(Clock.System.now())

    override val primaryKey = PrimaryKey(id)

    init {
        // Composite index for the common query: WHERE userId = ? ORDER BY generatedAt DESC
        index("idx_images_user_generated", false, userId, generatedAt)

        // Composite index for security checks: WHERE id = ? AND userId = ?
        index("idx_images_id_user", false, id, userId)

        // Index on model name if you filter by it
        index("idx_images_model", false, imageModelName)
    }
}

// Data class for GeneratedImage entity
data class GeneratedImageEntity(
    val id: String,
    val userId: String,
    val prompt: String,
    val imageData: ByteArray,
    val imageModelName: String,
    val generatedAt: Instant
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GeneratedImageEntity

        if (id != other.id) return false
        if (!imageData.contentEquals(other.imageData)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + imageData.contentHashCode()
        return result
    }
}

 */