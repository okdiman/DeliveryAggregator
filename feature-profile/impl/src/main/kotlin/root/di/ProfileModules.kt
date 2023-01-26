package root.di

import departure.root.di.departureModule
import editing.di.editingModule
import exit.di.exitModule

fun profileModules() = listOf(
    profileRootModule(),
    departureModule(),
    editingModule(),
    exitModule()
)