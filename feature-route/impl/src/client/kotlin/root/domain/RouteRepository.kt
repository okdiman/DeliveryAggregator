package root.domain

import orderchanges.domain.model.OrderChangesModel
import orderdetails.root.domain.model.OrderDetailsModel
import root.domain.model.NewOrderModel
import root.domain.model.OrderModel
import root.domain.model.RouteOrderModel
import root.domain.model.extras.OrderExtrasModel
import root.presentation.compose.model.RouteStorageModel

interface RouteRepository {
    suspend fun getOrderDetails(id: Long): RouteOrderModel
    suspend fun getClientOrderDetails(id: Long): OrderModel
    suspend fun getNewOrderPrice(model: NewOrderModel): Int
    suspend fun createNewOrder(model: NewOrderModel)
    suspend fun getExtras(): List<OrderExtrasModel>
    suspend fun getStorages(): List<RouteStorageModel>
    suspend fun getOrderRequests(): List<OrderDetailsModel>
    suspend fun getOrderChanges(id: Long): OrderChangesModel
    suspend fun confirmOrderChanges(id: Long)
}
