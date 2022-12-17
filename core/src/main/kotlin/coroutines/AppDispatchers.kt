package coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

interface AppDispatchers {
    val ui: MainCoroutineDispatcher
    val storage: CoroutineDispatcher
    val network: CoroutineDispatcher
    val computing: CoroutineDispatcher
}