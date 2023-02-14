package utils.ext

import android.graphics.Typeface
import android.text.Spannable
import android.text.style.StyleSpan

fun String.isTextFieldFilled(minChar: Int) = this.length >= minChar

fun Spannable.setBoldSpan() {
    setSpan(
        StyleSpan(Typeface.BOLD),
        0,
        length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}

fun Int.toStringWithEnding(ending: String = "₽") =
    buildString { append(this@toStringWithEnding.toString() + ending) }

fun Double.toStringWithEnding(ending: String = "₽") =
    buildString { append(this@toStringWithEnding.toString() + ending) }