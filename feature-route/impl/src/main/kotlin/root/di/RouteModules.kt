package root.di

import additionalInfo.di.additionalInfoModule
import notifications.di.notificationsModule
import orderdetails.di.orderDetailsModule

fun routeModules() =
    listOf(routeRootModule(), orderDetailsModule(), additionalInfoModule(), notificationsModule())