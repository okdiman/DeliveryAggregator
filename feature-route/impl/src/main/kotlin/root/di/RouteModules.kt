package root.di

import additionalInfo.di.additionalInfoModule
import orderdetails.di.orderDetailsModule

fun routeModules() = listOf(routeRootModule(), orderDetailsModule(), additionalInfoModule())