package trinity_monsters.wildberries_delivery_aggregator.di

import di.addressModule
import di.authModule
import di.coreModules
import di.coreUiiModule
import di.splashModule
import root.di.profileModules
import root.di.registrationModule
import root.di.routeModules

fun applicationModules() = listOf(
    coreUiiModule(),
    registrationModule(),
    addressModule(),
    splashModule()
) +
        coreModules() +
        authModule() +
        profileModules() +
        routeModules()