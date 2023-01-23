package utils

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.delay
import java.io.IOException

val processLifecycleScope: LifecycleCoroutineScope
    inline get() = ProcessLifecycleOwner.get().lifecycle.coroutineScope

@Suppress("LongParameterList", "TooGenericExceptionCaught")
suspend fun retryIO(
    times: Int = DEFAULT_TIMES,
    initialDelayInMillis: Long = DEFAULT_INITIAL_DELAY_DURATION,
    maxDelayInMillis: Long = DEFAULT_MAX_DELAY_DURATION,
    factor: Float = DEFAULT_FACTOR,
    action: suspend () -> Unit,
    onEachError: suspend (Throwable) -> Unit = {},
    onLastError: (Throwable) -> Unit = {}
) {
    var currentDelay = initialDelayInMillis

    var i = 0
    var wasSuccess = false
    var wasUnretryableError = false
    while (i < times && !wasSuccess && !wasUnretryableError) {
        try {
            action()
            wasSuccess = true
        } catch (e: IOException) {
            onEachError(e)
            if (i == times - 1) {
                onLastError(e)
            }
        } catch (e: Exception) {
            onLastError(e)
            wasUnretryableError = true
        }
        delay(currentDelay)
        currentDelay = (currentDelay * factor).toLong().coerceAtMost(maxDelayInMillis)
        i++
    }
}

private const val DEFAULT_INITIAL_DELAY_DURATION = 100L
private const val DEFAULT_MAX_DELAY_DURATION = 100L
private const val DEFAULT_FACTOR = 2.0F
private const val DEFAULT_TIMES = 1