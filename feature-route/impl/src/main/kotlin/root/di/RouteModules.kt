package root.di

import additionalInfo.di.additionalInfoModule
import notifications.di.notificationsModule
import orderdetails.deliverystate.di.deliveryStateModule
import orderdetails.loadingstate.di.loadingStateModule
import orderdetails.root.di.orderDetailsModule
import permissions.di.permissionsModule

fun routeModules() =
    listOf(
        routeRootModule(),
        orderDetailsModule(),
        additionalInfoModule(),
        notificationsModule(),
        permissionsModule(),
        loadingStateModule(),
        deliveryStateModule()
    )