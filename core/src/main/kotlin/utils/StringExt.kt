package utils

fun Int.toStringWithEnding(ending: String = "₽") =
    buildString { append(this@toStringWithEnding.toString() + ending) }

fun Double.toStringWithEnding(ending: String = "₽") =
    buildString { append(this@toStringWithEnding.toString() + ending) }