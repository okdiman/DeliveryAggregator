package utils

fun formatTicks(tick: Int): String {
    val formattedTick = if (tick.toString().length > 1) {
        tick.toString()
    } else {
        "0$tick"
    }
    return buildString {
        append(" 00:$formattedTick ")
    }
}