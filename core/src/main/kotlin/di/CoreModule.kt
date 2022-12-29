package di

import di.modules.clipboardModule
import di.modules.commonModule
import di.modules.networkModule
import di.modules.utilsModule

fun coreModules() = listOf(
    networkModule(),
    utilsModule(),
    commonModule(),
    clipboardModule()
)