package root.data

import neworder.root.data.mapper.NewOrderMapper
import orderdetails.root.domain.model.OrderDetailsModel
import root.data.mapper.RouteOrderMapper
import root.domain.RouteRepository
import root.domain.model.NewOrderModel
import root.domain.model.RouteOrderModel

class OrdersRouteRepositoryImpl(
    private val api: OrdersApi,
    private val orderMapper: RouteOrderMapper,
    private val newOrderMapper: NewOrderMapper
) : RouteRepository {

    override suspend fun getOrderRequests(): List<OrderDetailsModel> {
        val orderRequests = api.getClientOrders()
        return orderRequests.orders?.map(orderMapper::mapOrderToDomain).orEmpty()
    }

    override suspend fun getOrderDetails(id: Long): RouteOrderModel {
        val request = api.getOrderDetails(id)
        return orderMapper.map(request)
    }

    override suspend fun getNewOrderPrice(model: NewOrderModel) = api.getOrderPrice(newOrderMapper.map(model)).price

    override suspend fun createNewOrder(model: NewOrderModel) {
        api.createOrder(newOrderMapper.map(model))
    }

    override suspend fun getExtras() = api.getExtras().extras.map {
        orderMapper.mapExtrasToDomain(it)
    }

    override suspend fun getStorages() = api.getStorages().request.map {
        orderMapper.mapStorageToDomain(it)
    }
}