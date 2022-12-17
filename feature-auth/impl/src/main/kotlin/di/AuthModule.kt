package di

import login.di.loginModule
import offer.di.offerModule
import root.di.rootModule
import verify.di.verifyModule

fun authModule() = listOf(
    loginModule(),
    offerModule(),
    verifyModule(),
    rootModule()
)