package org.yassineabou.playground.feature.chat.data.network

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


object HtmlParser {
    fun parse(html: String): AnnotatedString {
        val builder = AnnotatedString.Builder()
        val tagStack = mutableListOf<Triple<String, SpanStyle, Int>>()
        var currentPos = 0

        val tagRegex = Regex("""<(\/?)(\w+)(?:[^>]*?)>""")

        tagRegex.findAll(html).forEach { match ->
            val (prefix, tagName) = match.destructured
            val tag = tagName.lowercase()

            builder.append(html.substring(currentPos, match.range.first))

            if (prefix.isEmpty()) {
                // Store tag name with style
                val style = when (tag) {
                    "strong", "b" -> SpanStyle(fontWeight = FontWeight.Bold)
                    "em", "i" -> SpanStyle(fontStyle = FontStyle.Italic)
                    "code" -> SpanStyle(
                        background = Color.Black,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White
                    )
                    "h1" -> SpanStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    "h2" -> SpanStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    else -> SpanStyle()
                }
                tagStack.add(Triple(tag, style, builder.length))
            } else {
                // Find matching tag
                val lastIndex = tagStack.indexOfLast { it.first == tag }
                if (lastIndex != -1) {
                    val (_, style, start) = tagStack[lastIndex]
                    builder.addStyle(style, start, builder.length)
                    tagStack.removeAt(lastIndex)
                }
            }

            currentPos = match.range.last + 1
        }

        builder.append(html.substring(currentPos))
        return builder.toAnnotatedString()
    }
}