package di

import di.modules.commonModule
import di.modules.networkModule
import di.modules.networkStateModule
import di.modules.utilsModule

fun coreModules() = listOf(
    networkModule(),
    utilsModule(),
    commonModule(),
    networkStateModule()
)