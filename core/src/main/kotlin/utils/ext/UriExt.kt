package utils.ext

import android.net.Uri

fun Uri.getSafeQueryParameter(name: String): String? = runCatching {
    getQueryParameter(name)
}.getOrNull()