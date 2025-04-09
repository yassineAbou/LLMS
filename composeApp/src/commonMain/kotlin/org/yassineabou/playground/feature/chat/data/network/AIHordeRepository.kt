package org.yassineabou.playground.feature.chat.data.network

import co.touchlab.kermit.Logger
import kotlinx.coroutines.delay
import org.yassineabou.playground.feature.chat.data.model.GenerationParams
import org.yassineabou.playground.feature.chat.data.model.GenerationRequest

class AIHordeRepository(private val aiHordeApi: AIHordeApi) {

    suspend fun generateText(
        apiKey: String,
        prompt: String,
        //modelTitle: String, // Add model title parameter
        params: GenerationParams = GenerationParams(),
        checkInterval: Long = 2000L
    ): Result<String> {
        return try {
            // Include selected model in the request
            val log = Logger.withTag("/v2/generate/text/async")
            val request = GenerationRequest(
                prompt = prompt,
                params = params,
                //models = listOf(modelTitle)
            )
            val response = aiHordeApi.submitGeneration(apiKey, request)
            log.e("Async Text Return Id: ${response.id}")
            pollForCompletion(response.id, checkInterval)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun pollForCompletion(id: String, interval: Long): Result<String> {
        while (true) {
            val status = aiHordeApi.checkGenerationStatus(id)
            when {
                status.done -> {
                    val result = status.generations?.firstOrNull()?.text
                        ?: return Result.failure(Exception("Empty response"))
                    return Result.success(result)
                }
                status.faulted -> return Result.failure(Exception("Generation faulted"))
                else -> delay(interval)
            }
        }
    }
}