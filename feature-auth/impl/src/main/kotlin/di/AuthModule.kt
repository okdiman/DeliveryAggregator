package di

import offer.di.offerModule
import login.di.startModule
import verify.di.verifyModule

fun authModule() = listOf(
    startModule(),
    offerModule(),
    verifyModule()
)