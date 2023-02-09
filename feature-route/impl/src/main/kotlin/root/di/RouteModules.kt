package root.di

import additionalInfo.di.additionalInfoModule
import notifications.di.notificationsModule
import orderdetails.root.di.orderDetailsModule
import permissions.di.permissionsModule

fun routeModules() =
    listOf(routeRootModule(), orderDetailsModule(), additionalInfoModule(), notificationsModule(), permissionsModule())