package org.yassineabou.playground.feature.chat.data.network

import androidx.compose.ui.text.AnnotatedString
import co.touchlab.kermit.Logger


class GitHubMarkdownRepository(
    private val api: GitHubMarkdownApi
) {

    suspend fun renderMarkdown(text: String): String {
        return try {
            api.renderMarkdown(text)
        } catch (e: Exception) {
            // Fallback to raw text if rendering fails
            Logger.e(e) { "Markdown rendering failed" }
            text
        }
    }


    fun parseHtmlToAnnotatedString(html: String): AnnotatedString {
        return try {
            HtmlParser.parse(html)
        } catch (e: Exception) {
            Logger.e(e) { "HTML parsing failed" }
            AnnotatedString(html)
        }
    }
}