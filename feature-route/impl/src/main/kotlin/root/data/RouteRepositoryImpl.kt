package root.data

import root.data.mapper.RouteMapper
import root.data.mapper.RouteOrderMapper
import root.domain.RouteRepository
import root.domain.model.RouteOrderModel
import root.domain.model.RouteModel

class RouteRepositoryImpl(
    private val api: RouteApi,
    private val orderMapper: RouteOrderMapper,
    private val routeMapper: RouteMapper
) : RouteRepository {
    override suspend fun getActiveRoute(): RouteModel {
        val request = api.getActiveRoute()
        return routeMapper.map(request)
    }

    override suspend fun getOrderDetails(id: Long): RouteOrderModel {
        val request = api.getOrderDetails(id)
        return orderMapper.map(request)
    }

    override suspend fun acceptRoute(id: Long) {
        api.acceptRoute(id)
    }
}