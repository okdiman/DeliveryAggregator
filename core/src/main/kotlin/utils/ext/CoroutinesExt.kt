package utils.ext

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.isActive

@OptIn(ExperimentalCoroutinesApi::class)
inline fun <reified E : Any> ProducerScope<E>.offerSafe(element: E) {
    if (isActive && !isClosedForSend) {
        trySend(element)
    }
}

val processLifecycleScope: LifecycleCoroutineScope
    inline get() = ProcessLifecycleOwner.get().lifecycle.coroutineScope