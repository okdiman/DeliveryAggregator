package di

import login.di.loginModule
import root.di.authRootModule
import verify.di.verifyModule

fun authModule() = listOf(
    loginModule(),
    verifyModule(),
    authRootModule()
)