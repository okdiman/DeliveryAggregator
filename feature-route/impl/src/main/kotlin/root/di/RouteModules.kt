package root.di

import additionalInfo.di.additionalInfoModule
import notifications.root.di.notificationsModule
import orderdetails.root.di.orderDetailsModule

fun routeModules() =
    listOf(routeRootModule(), orderDetailsModule(), additionalInfoModule(), notificationsModule())