package root.domain

import orderdetails.loadingstate.domain.model.LoadingStateRequestModel
import root.domain.model.RouteModel
import root.domain.model.RouteOrderModel

interface RouteRepository {
    suspend fun getActiveRoute(): RouteModel
    suspend fun getOrderDetails(id: Long): RouteOrderModel
    suspend fun acceptRoute(id: Long)
    suspend fun confirmLoadingState(id: Long, model: LoadingStateRequestModel)
}