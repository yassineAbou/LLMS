package org.yassineabou.playground.feature.chat.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface GitHubMarkdownApi {
    suspend fun renderMarkdown(text: String): String
}

class GitHubMarkdownApiImpl(private val client: HttpClient) : GitHubMarkdownApi {
    override suspend fun renderMarkdown(text: String): String {
        return client.post("https://api.github.com/markdown") {
            contentType(ContentType.Application.Json)
            header("X-GitHub-Api-Version", "2022-11-28")
            setBody(mapOf(
                "text" to text,
                "mode" to "gfm"
            ))
        }.body()
    }
}