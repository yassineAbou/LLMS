package org.yassineabou.llms.api

import kotlinx.coroutines.flow.Flow
import kotlinx.rpc.annotations.Rpc
import kotlinx.serialization.Serializable

/**
 * DTO for a generated image.
 * The binary imageData is handled as a ByteArray, which is a multiplatform type.
 * The generatedAt timestamp is converted to a String for serialization.
 */
@Serializable
data class GeneratedImageDto(
    val id: String,
    val userId: String,
    val prompt: String,
    val imageData: ByteArray, // Serialized as Base64 in JSON by default
    val imageModelName: String,
    val generatedAt: String
) {
    // Custom equals/hashCode for ByteArray comparison is good practice
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as GeneratedImageDto

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
/*
/**
 * The RPC interface for all image generation and management operations.
 */
@Rpc
interface ImageService {

    /**
     * Stores a new generated image for a user.
     * The client provides the raw data, and the server assigns an ID and timestamp.
     * @return The complete GeneratedImageDto as it was stored in the database.
     */
    suspend fun createImage(
        id: String,
        userId: String,
        prompt: String,
        imageData: ByteArray,
        imageModelName: String
    ): GeneratedImageDto

    /**
     * Retrieves a list of all previously generated images for a user.
     */
    suspend fun getImagesForUser(userId: String): Flow<GeneratedImageDto>

    /**
     * Deletes a specific image by its ID.
     * @return The number of images deleted (should be 1 or 0).
     */
    suspend fun deleteImage(userId: String, imageId: String): Int

    /**
     * Deletes all images for a given user.
     * @return The total number of images deleted.
     */
    suspend fun clearAllImagesForUser(userId: String): Int
}

 */