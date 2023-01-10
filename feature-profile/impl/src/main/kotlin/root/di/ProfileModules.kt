package root.di

import deleting.di.deletingModule
import departure.root.di.departureModule
import editing.di.editingModule
import exit.di.exitModule

fun profileModules() = listOf(
    profileRootModule(),
    departureModule(),
    editingModule(),
    deletingModule(),
    exitModule()
)