package utils

import android.graphics.Typeface
import android.text.Spannable
import android.text.style.StyleSpan

fun isTextFieldFilled(newText: String, minChar: Int) = newText.length >= minChar

fun Spannable.setBoldSpan() {
    setSpan(
        StyleSpan(Typeface.BOLD),
        0,
        length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}