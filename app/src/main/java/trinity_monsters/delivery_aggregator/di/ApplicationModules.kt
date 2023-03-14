package trinity_monsters.delivery_aggregator.di

import di.authModule
import di.coreModules
import di.coreUiiModule
import di.imageUploadingModule
import di.splashModule
import root.di.addressModule
import root.di.devMenuModule
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
    imageUploadingModule(),
    devMenuModule()
) +
    coreModules() +
    authModule() +
    profileModules() +
    routeModules()