package utils.formatters

import android.text.SpannableStringBuilder
import android.text.Spanned
import java.util.regex.Pattern

object SpannableFormatter {

    private val PATTERN = Pattern.compile("%([0-9]+\\$|<?)([^a-zA-z%]*)([[a-zA-Z%]&&[^tT]]|[tT][a-zA-Z])")

    /**
     * Аналог String.format но для Spanned
     * Взято отсюда:
     * [https://github.com/george-steel/android-utils/blob/master/
     * src/org/oshkimaadziig/george/androidutils/SpanFormatter.java]
     */
    @Suppress("ComplexMethod", "ImplicitDefaultLocale", "LoopWithTooManyJumpStatements")
    fun format(source: CharSequence, vararg args: Any): Spanned {
        val result = SpannableStringBuilder(source)
        var i = 0
        var argAt = -1
        while (i < result.length) {
            val m = PATTERN.matcher(result)
            if (!m.find(i)) {
                break
            }
            i = m.start()
            val exprEnd = m.end()

            val argTerm = m.group(1) ?: continue
            val modTerm = m.group(2) ?: continue
            val typeTerm = m.group(3) ?: continue

            val cookedArg = when (typeTerm) {
                "%" -> "%"
                "n" -> "\n"
                else -> {
                    val argIdx = when (argTerm) {
                        "" -> ++argAt
                        "<" -> argAt
                        else -> argTerm.dropLast(1).toInt() - 1
                    }
                    val argItem = args[argIdx]
                    if (typeTerm == "s" && argItem is Spanned) {
                        argItem
                    } else {
                        String.format("%$modTerm$typeTerm", argItem)
                    }
                }
            }

            result.replace(i, exprEnd, cookedArg)
            i += cookedArg.length
        }
        return result
    }
}