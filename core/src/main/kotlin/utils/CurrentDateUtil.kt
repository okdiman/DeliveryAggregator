package utils

import org.threeten.bp.format.DateTimeFormatter
import utils.ext.toLocalZonedDateTime
import utils.ext.toString

class CurrentDateUtil {
    fun getDate(vararg formatter: DateTimeFormatter, separator: String): String {
        val point = System.currentTimeMillis().toLocalZonedDateTime()
        var result = ""
        formatter.forEachIndexed { index, format ->
            if (index != 0) {
                result += separator
            }
            result += point.toString(format)
        }
        return result
    }
}