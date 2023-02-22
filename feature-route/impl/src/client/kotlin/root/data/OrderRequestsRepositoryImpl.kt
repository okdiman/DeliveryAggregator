package root.data

import root.data.mapper.OrderMapper
import root.domain.OrderRequestsRepository
import root.domain.model.OrderModel

class OrderRequestsRepositoryImpl(
    private val api: OrderApi,
    private val orderMapper: OrderMapper,
) : OrderRequestsRepository {

    override suspend fun getOrderRequests(): List<OrderModel> {
        val orderRequests = api.getOrderRequests()
        return orderRequests.orders?.map(orderMapper::mapOrderToDomain).orEmpty()
    }
}
