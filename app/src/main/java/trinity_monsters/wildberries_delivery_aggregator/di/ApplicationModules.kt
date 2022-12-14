package trinity_monsters.wildberries_delivery_aggregator.di

import auth.di.authModule
import di.networkModule

fun applicationModules() = listOf(
    networkModule(),
    authModule()
)