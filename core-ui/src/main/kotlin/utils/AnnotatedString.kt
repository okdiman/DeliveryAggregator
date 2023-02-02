package utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

fun getAnnotatedString(text: String, words: List<String>): AnnotatedString = buildAnnotatedString {
    append(text)
    words.forEach { word ->
        if (text.contains(word)) {
            val offsetStart = text.indexOf(word)
            val offsetEnd = offsetStart + word.length
            addStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold),
                start = offsetStart,
                end = offsetEnd
            )
        }
    }
}