package di

import login.di.loginModule
import offer.di.offerModule
import root.di.authRootModule
import verify.di.verifyModule

fun authModule() = listOf(
    loginModule(),
    offerModule(),
    verifyModule(),
    authRootModule()
)