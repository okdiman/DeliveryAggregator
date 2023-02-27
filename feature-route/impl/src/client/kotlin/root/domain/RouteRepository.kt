package root.domain

import orderdetails.root.domain.model.OrderDetailsModel
import root.domain.model.NewOrderModel
import root.domain.model.RouteOrderModel
import root.domain.model.extras.OrderExtrasModel
import root.presentation.compose.model.RouteStorageModel

interface RouteRepository {
    suspend fun getOrderDetails(id: Long): RouteOrderModel
    suspend fun getNewOrderPrice(model: NewOrderModel): Int
    suspend fun createNewOrder(model: NewOrderModel)
    suspend fun getExtras(): List<OrderExtrasModel>
    suspend fun getStorages(): List<RouteStorageModel>
    suspend fun getOrderRequests(): List<OrderDetailsModel>
}