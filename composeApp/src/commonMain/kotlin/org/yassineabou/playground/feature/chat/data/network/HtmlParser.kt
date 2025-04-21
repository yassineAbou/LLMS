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

            // Append text before the current tag
            builder.append(html.substring(currentPos, match.range.first))

            if (prefix.isEmpty()) {
                // Opening tag - build style and push to stack
                val style = when (tag) {
                    "strong", "b" -> SpanStyle(fontWeight = FontWeight.Bold)
                    "em", "i" -> SpanStyle(fontStyle = FontStyle.Italic)
                    "code" -> SpanStyle(
                        background = Color(0xFFE5E7EB),
                        color = Color(0xFF1F2937),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp
                    )
                    "pre" -> SpanStyle(
                        background = Color(0xFFF3F4F6),
                        color = Color(0xFF1F2937),
                        fontFamily = FontFamily.Monospace,
                        fontSize = 14.sp
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
                // Closing tag - apply style from stack
                val lastIndex = tagStack.indexOfLast { it.first == tag }
                if (lastIndex != -1) {
                    val (_, style, start) = tagStack[lastIndex]

                    // Add line break before pre blocks
                    if (tag == "pre") {
                        builder.append("\n")
                    }

                    builder.addStyle(style, start, builder.length)
                    tagStack.removeAt(lastIndex)
                }
            }

            currentPos = match.range.last + 1
        }

        // Append remaining text and handle newlines
        val remainingText = html.substring(currentPos)
        builder.append(remainingText.replace("\n", "\n"))

        return builder.toAnnotatedString()
    }
}