package trinity_monsters.wildberries_delivery_aggregator.di

import di.authModule
import di.coreModules
import di.coreUiiModule

fun applicationModules() = listOf(
    authModule(),
    coreUiiModule()
) + coreModules()