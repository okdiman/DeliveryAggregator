package utils.ext

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import utils.ext.DateFormats.ZONE_ID_UTC
import java.util.Locale

fun Long.toLocalZonedDateTime(): LocalDateTime {
    val localDateTime = Instant.ofEpochMilli(this).atZone(ZONE_ID_UTC).toLocalDateTime()
    return ZonedDateTime.of(localDateTime, ZONE_ID_UTC).toLocalLocalDateTime()
}

fun String.toLocalZonedDateTime(formatter: DateTimeFormatter): LocalDateTime {
    return ZonedDateTime.of(this.toLocalDateTime(formatter), ZONE_ID_UTC)
        .toLocalLocalDateTime()
}

fun String.toLocalDateTime(formatter: DateTimeFormatter): LocalDateTime {
    return LocalDateTime.parse(this, formatter)
}

fun String.toLocalDate(pattern: String): LocalDate = LocalDate.parse(this, DateTimeFormatter.ofPattern(pattern))

fun ZonedDateTime.toLocalLocalDateTime(): LocalDateTime {
    return withZoneSameInstant(ZoneId.systemDefault())
        .toLocalDateTime()
}

fun LocalDateTime.toString(pattern: String): String {
    val formatter = when (pattern) {
        DateFormats.FULL_DATE_TIME -> {
            DateFormats.FULL_DATE_TIME_FORMATTER
        }
        DateFormats.FULL_DISPLAYED_DATE_TIME -> {
            DateFormats.FULL_DISPLAYED_DATE_TIME_FORMATTER
        }
        else -> {
            DateTimeFormatter.ofPattern(pattern)
        }
    }
    return toString(formatter)
}

fun LocalDateTime.toString(formatter: DateTimeFormatter): String {
    return format(formatter)
}

object DateFormats {
    const val FULL_DISPLAYED_DAY_MONTH = "d MMMM"
    private const val TIME = "HH:mm"
    const val DAY_MONTH_YEAR_FORMAT = "d MMMM yyyy"
    const val MONTH_FORMAT = "LLLL"
    const val FULL_DATE_TIME = "yyyy-MM-dd HH:mm"
    const val YEAR_MONTH_DAY = "yyyy-MM-dd"
    const val FULL_DISPLAYED_DATE_TIME = "d MMMM HH:mm"
    const val DOT_DAY_FORMAT = "dd.MM.yyyy"
    private val LOCALE_RU = Locale("ru", "RU")
    val FULL_DATE_TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(FULL_DATE_TIME)
    val FULL_DISPLAYED_DATE_TIME_FORMATTER: DateTimeFormatter =
        DateTimeFormatter.ofPattern(FULL_DISPLAYED_DATE_TIME)
            .withLocale(LOCALE_RU)
    val FULL_DISPLAYED_DAY_MONTH_FORMATTER: DateTimeFormatter =
        DateTimeFormatter.ofPattern(FULL_DISPLAYED_DAY_MONTH)
    val TIME_FORMATTER: DateTimeFormatter =
        DateTimeFormatter.ofPattern(TIME)
    val ZONE_ID_UTC: ZoneId = ZoneId.ofOffset("UTC", ZoneOffset.UTC)
}