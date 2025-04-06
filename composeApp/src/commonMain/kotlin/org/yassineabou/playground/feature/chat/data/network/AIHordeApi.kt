package org.yassineabou.playground.feature.chat.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.yassineabou.playground.feature.chat.data.model.GenerationRequest
import org.yassineabou.playground.feature.chat.data.model.GenerationResponse
import org.yassineabou.playground.feature.chat.data.model.GenerationStatus

interface AIHordeApi {
    suspend fun submitGeneration(apiKey: String, request: GenerationRequest): GenerationResponse
    suspend fun checkGenerationStatus(id: String): GenerationStatus
}

class KtorAIHordeApi(private val client: HttpClient) : AIHordeApi {
    companion object {
        private const val BASE_URL = "https://stablehorde.net/api/v2"
    }

    override suspend fun submitGeneration(apiKey: String, request: GenerationRequest): GenerationResponse {
        return client.post("$BASE_URL/generate/text/async") {
            header("apikey", apiKey)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    override suspend fun checkGenerationStatus(id: String): GenerationStatus {
        return client.get("$BASE_URL/generate/text/status/$id").body()
    }
}