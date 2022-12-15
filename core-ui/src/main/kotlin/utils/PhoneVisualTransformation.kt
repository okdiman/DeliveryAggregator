package utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation(
    private val maskNumber: Char = '0'
) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.length > MAX_PHONE_LENGTH) text.take(MAX_PHONE_LENGTH) else text
        val annotatedString = buildAnnotatedString {
            if (trimmed.isEmpty()) return@buildAnnotatedString
            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < DEFAULT_MASK.length) {
                if (DEFAULT_MASK[maskIndex] != maskNumber) {
                    val nextDigitIndex = DEFAULT_MASK.indexOf(maskNumber, maskIndex)
                    append(DEFAULT_MASK.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
        }
        return TransformedText(annotatedString, PhoneOffsetMapper(DEFAULT_MASK, maskNumber))
    }

    private class PhoneOffsetMapper(val mask: String, val numberChar: Char) : OffsetMapping {

        override fun originalToTransformed(offset: Int): Int {
            var noneDigitCount = 0
            var i = 0
            while (i < offset + noneDigitCount) {
                if (mask[i++] != numberChar) noneDigitCount++
            }
            return offset + noneDigitCount
        }

        override fun transformedToOriginal(offset: Int): Int =
            offset - mask.take(offset).count { it != numberChar }
    }

    companion object {
        private const val DEFAULT_MASK = "(000)-000-00-00"
        private const val MAX_PHONE_LENGTH = 10
    }
}