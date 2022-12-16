package di.modules

import coroutines.AppDispatchers
import coroutines.JvmAppDispatchers
import org.koin.dsl.module

fun commonModule() = module {
    factory<AppDispatchers> { JvmAppDispatchers() }
}