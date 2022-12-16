package coroutines

import kotlinx.coroutines.Dispatchers

class JvmAppDispatchers : AppDispatchers {
    override val ui = Dispatchers.Main
    override val storage = Dispatchers.IO
    override val network = Dispatchers.IO
    override val computing = Dispatchers.Default
}