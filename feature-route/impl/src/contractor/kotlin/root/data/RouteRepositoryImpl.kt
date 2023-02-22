package root.data

import orderdetails.deliverystate.data.mapper.DeliveryStateMapper
import orderdetails.deliverystate.domain.model.DeliveryStateRequestModel
import orderdetails.loadingstate.data.mapper.LoadingStateMapper
import orderdetails.loadingstate.domain.model.LoadingStateRequestModel
import root.data.mapper.RouteMapper
import root.data.mapper.OrderMapper
import root.domain.RouteRepository
import root.domain.model.RouteModel
import root.domain.model.RouteOrderModel

class RouteRepositoryImpl(
    private val api: RouteApi,
    private val orderMapper: OrderMapper,
    private val routeMapper: RouteMapper,
    private val loadingStateMapper: LoadingStateMapper,
    private val deliveryStateMapper: DeliveryStateMapper
) : RouteRepository {
    override suspend fun getActiveRoute(): RouteModel {
        val request = api.getActiveRoute()
        return routeMapper.map(request)
    }

    override suspend fun getOrderDetails(id: Long): RouteOrderModel {
        val request = api.getOrderDetails(id)
        return routeMapper.mapRouteOrder(request)
    }

    override suspend fun acceptRoute(id: Long) {
        api.acceptRoute(id)
    }

    override suspend fun confirmLoadingState(id: Long, model: LoadingStateRequestModel) {
        api.confirmLoadingState(id, loadingStateMapper.map(model))
    }

    override suspend fun confirmDeliveryState(id: Long, model: DeliveryStateRequestModel) {
        api.confirmDeliveryState(id, deliveryStateMapper.map(model))
    }

    override suspend fun getExtras() = api.getExtras().extras.map {
        orderMapper.mapExtrasToDomain(it)
    }
}
