package trinity_monsters.delivery_aggregator.di

import root.di.addressModule
import di.authModule
import di.coreModules
import di.coreUiiModule
import di.imageUploadingModule
import di.splashModule
import root.di.profileModules
import root.di.registrationModule
import root.di.routeModules
import trinity_monsters.delivery_aggregator.notifications.di.remoteNotificationsModule

fun applicationModules() = listOf(
    applicationRootModule(),
    coreUiiModule(),
    registrationModule(),
    addressModule(),
    splashModule(),
    remoteNotificationsModule(),
    imageUploadingModule()
) +
    coreModules() +
    authModule() +
    profileModules() +
    routeModules()