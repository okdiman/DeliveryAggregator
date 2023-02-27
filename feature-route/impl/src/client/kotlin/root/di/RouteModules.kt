package root.di

import additionalInfo.di.additionalInfoModule
import extras.di.extrasModule
import neworder.root.di.newOrderModule
import neworder.storage.di.newOrderStorageModule
import notifications.di.notificationsModule
import orderdetails.root.di.orderDetailsModule
import permissions.di.permissionsModule

fun routeModules() =
    listOf(
        routeRootModule(),
        orderDetailsModule(),
        additionalInfoModule(),
        notificationsModule(),
        permissionsModule(),
        newOrderStorageModule(),
        newOrderModule(),
        extrasModule()
    )