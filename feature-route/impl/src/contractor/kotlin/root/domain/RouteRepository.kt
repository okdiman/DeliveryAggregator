package root.domain

import orderdetails.deliverystate.domain.model.DeliveryStateRequestModel
import orderdetails.loadingstate.domain.model.LoadingStateRequestModel
import root.domain.model.extras.OrderExtrasModel
import root.domain.model.RouteModel
import root.domain.model.RouteOrderModel

interface RouteRepository {
    suspend fun getActiveRoute(): RouteModel
    suspend fun getOrderDetails(id: Long): RouteOrderModel
    suspend fun acceptRoute(id: Long)
    suspend fun confirmLoadingState(id: Long, model: LoadingStateRequestModel)
    suspend fun confirmDeliveryState(id: Long, model: DeliveryStateRequestModel)
    suspend fun getExtras(): List<OrderExtrasModel>
}
