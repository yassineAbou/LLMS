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
        val processedHtml = preprocessHtml(html)

        val builder = AnnotatedString.Builder()
        val tagStack = mutableListOf<Triple<String, SpanStyle, Int>>()
        var currentPos = 0

        val tagRegex = Regex("""<(\/?)(\w+)(?:[^>]*?)>""")

        tagRegex.findAll(processedHtml).forEach { match ->
            val (prefix, tagName) = match.destructured
            val tag = tagName.lowercase()

            // Append text before the current tag
            builder.append(processedHtml.substring(currentPos, match.range.first))

            if (prefix.isEmpty()) {
                // Opening tag handling
                val style = when (tag) {
                    "strong", "b" -> SpanStyle(fontWeight = FontWeight.Bold)
                    "em", "i" -> SpanStyle(fontStyle = FontStyle.Italic)
                    "code" -> codeStyle()
                    "pre" -> preStyle()
                    "h1" -> h1Style()
                    "h2" -> h2Style()
                    else -> SpanStyle()
                }
                tagStack.add(Triple(tag, style, builder.length))
            } else {
                // Closing tag handling
                val lastIndex = tagStack.indexOfLast { it.first == tag }
                if (lastIndex != -1) {
                    val (_, style, start) = tagStack[lastIndex]
                    if (tag == "pre") builder.append("\n")
                    builder.addStyle(style, start, builder.length)
                    tagStack.removeAt(lastIndex)
                }
            }

            currentPos = match.range.last + 1
        }

        // Process remaining text
        val remainingText = processedHtml.substring(currentPos)
            .replace(Regex("""\s*\n+\s*"""), "\n") // Normalize newlines
            .trim()

        builder.append(remainingText)

        return builder.toAnnotatedString()
    }

    private fun preprocessHtml(html: String): String {
        return html
            // Remove special tags
            .replace(Regex("""(?s)<(?:reasoning|sep|think)[^>]*>.*?</(?:reasoning|sep|think)>"""), "")
            .replace(Regex("""<(?:reasoning|sep|think)[^>]*>"""), "")

            // Convert markdown to HTML
            .replace(Regex("""^#{1,6}\s+""", RegexOption.MULTILINE), "") // Remove headings
            .replace(Regex("""(\*\*|__)(.*?)\1"""), "<strong>$2</strong>")
            .replace(Regex("""(\*|_)(.*?)\1"""), "<em>$2</em>")
            .replace(Regex("""`(.*?)`"""), "<code>$1</code>")
            .replace(Regex("""(?s)```(.*?)```"""), "<pre>$1</pre>")

            // Clean remaining artifacts
            .replace(Regex("""[#=*_]+"""), " ")
            .replace(Regex("""\s{2,}"""), " ")
            .trim()
    }

    private fun codeStyle() = SpanStyle(
        background = Color(0xFFE5E7EB),
        fontFamily = FontFamily.Monospace,
        fontSize = 14.sp
    )

    private fun preStyle() = SpanStyle(
        background = Color(0xFFF3F4F6),
        fontFamily = FontFamily.Monospace,
        fontSize = 14.sp
    )

    private fun h1Style() = SpanStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )

    private fun h2Style() = SpanStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}