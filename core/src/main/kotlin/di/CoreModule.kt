package di

import di.modules.clipboardModule
import di.modules.commonModule
import di.modules.networkModule
import di.modules.utilsModule
import di.modules.networkStateModule

fun coreModules() = listOf(
    networkModule(),
    utilsModule(),
    commonModule(),
    clipboardModule(),
    networkStateModule()
)