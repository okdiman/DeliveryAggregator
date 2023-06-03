package utils.ext

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.style.StyleSpan
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

fun String.isTextFieldFilled(minChar: Int) = this.length >= minChar

fun Spannable.setBoldSpan() {
    setSpan(
        StyleSpan(Typeface.BOLD),
        0,
        length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
}

fun BigDecimal.asPriceInRubles(): String {
    val scaled = setScale(2, RoundingMode.HALF_EVEN)
    val fractionalPart = remainder(BigDecimal.ONE)
    val isBigDecimalAnInteger = fractionalPart.compareTo(BigDecimal.ZERO) == 0
    val max = if (isBigDecimalAnInteger) 0 else 2
    val df = DecimalFormat().apply {
        minimumFractionDigits = 2
        maximumFractionDigits = max // Обновит minimumFractionDigits в случае нуля
        isGroupingUsed = false
    }

    return "${df.format(scaled)}₽"
}

fun Drawable.getBitmapFromVector(): Bitmap {
    val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, canvas.width, canvas.height)
    draw(canvas)
    return bitmap
}
