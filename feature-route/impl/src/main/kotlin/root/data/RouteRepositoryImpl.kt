package root.data

import orderdetails.loadingstate.data.mapper.LoadingStateMapper
import orderdetails.loadingstate.domain.model.LoadingStateRequestModel
import root.data.mapper.RouteMapper
import root.data.mapper.RouteOrderMapper
import root.domain.RouteRepository
import root.domain.model.RouteModel
import root.domain.model.RouteOrderModel

class RouteRepositoryImpl(
    private val api: RouteApi,
    private val orderMapper: RouteOrderMapper,
    private val routeMapper: RouteMapper,
    private val loadingStateMapper: LoadingStateMapper
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

    override suspend fun confirmLoadingState(id: Long, model: LoadingStateRequestModel) {
        api.confirmLoadingState(id, loadingStateMapper.map(model))
    }
}