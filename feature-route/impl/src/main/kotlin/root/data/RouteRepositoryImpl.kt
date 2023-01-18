package root.data

import root.data.mapper.RouteMapper
import root.domain.RouteRepository
import root.domain.model.RouteModel

class RouteRepositoryImpl(
    private val api: RouteApi,
    private val mapper: RouteMapper
) : RouteRepository {
    override suspend fun getActiveRoute(): RouteModel {
        val request = api.getActiveRoute()
        return mapper.map(request)
    }
}