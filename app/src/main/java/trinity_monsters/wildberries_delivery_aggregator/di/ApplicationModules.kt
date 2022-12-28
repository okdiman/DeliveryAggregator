package trinity_monsters.wildberries_delivery_aggregator.di

import di.addressModule
import di.authModule
import di.coreModules
import di.coreUiiModule
import di.splashModule
import root.di.profileModule
import root.di.registrationModule

fun applicationModules() = listOf(
    coreUiiModule(),
    registrationModule(),
    addressModule(),
    profileModule(),
    splashModule()
) +
        coreModules() +
        authModule()