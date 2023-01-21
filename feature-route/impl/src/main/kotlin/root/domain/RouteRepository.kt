package root.domain

import root.domain.model.RouteOrderModel
import root.domain.model.RouteModel

interface RouteRepository {
    suspend fun getActiveRoute(): RouteModel
    suspend fun getOrderDetails(id: Long) : RouteOrderModel
}