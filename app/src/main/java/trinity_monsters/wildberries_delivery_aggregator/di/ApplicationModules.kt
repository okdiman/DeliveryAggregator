package trinity_monsters.wildberries_delivery_aggregator.di

import di.authModule
import di.coreModules
import di.coreUiiModule
import root.di.registrationModule

fun applicationModules() = listOf(
    coreUiiModule(),
    registrationModule()
) +
        coreModules() +
        authModule()