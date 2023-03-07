package utils.ext

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Typeface
import android.graphics.drawable.Drawable
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

fun Drawable.getBitmapFromVector(): Bitmap {
    val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)
    return bitmap
}