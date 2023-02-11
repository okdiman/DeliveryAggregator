package root.domain

import root.domain.model.RouteModel
import root.domain.model.RouteOrderModel

interface RouteRepository {
    suspend fun getActiveRoute(): RouteModel
    suspend fun getOrderDetails(id: Long): RouteOrderModel
    suspend fun acceptRoute(id: Long)
}