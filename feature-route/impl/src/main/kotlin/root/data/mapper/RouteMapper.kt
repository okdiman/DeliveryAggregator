package root.data.mapper

import root.data.model.RouteDto
import root.domain.model.RouteStatusProgress
import root.domain.model.RouteModel

class RouteMapper(
    private val orderMapper: RouteOrderMapper
) {
    fun map(dto: RouteDto): RouteModel {
        return RouteModel(
            id = dto.id,
            orders = dto.orders.map { orderMapper.map(it) },
            distance = dto.distance,
            price = dto.totalPrice,
            status = RouteStatusProgress.values().first { it.status == dto.status }
        )
    }
}