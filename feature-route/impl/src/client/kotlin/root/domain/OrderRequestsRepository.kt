package root.domain

import root.domain.model.OrderModel

interface OrderRequestsRepository {
    suspend fun getOrderRequests(): List<OrderModel>
}
