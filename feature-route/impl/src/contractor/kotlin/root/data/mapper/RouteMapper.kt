package root.data.mapper

import orderdetails.root.domain.model.details.OrderDetailsClientModel
import root.data.model.response.RouteDto
import root.data.model.response.RouteOrderDto
import root.domain.model.RouteStatusProgress
import root.domain.model.RouteModel
import root.domain.model.RouteOrderModel

class RouteMapper(
    private val orderMapper: OrderMapper
) {
    fun map(dto: RouteDto): RouteModel {
        return RouteModel(
            id = dto.id,
            orders = dto.orders.map { mapRouteOrder(it) },
            distance = dto.distance,
            price = dto.totalPrice,
            status = RouteStatusProgress.values().first { it.status == dto.status }
        )
    }

    fun mapRouteOrder(dto: RouteOrderDto) = RouteOrderModel(
        details = orderMapper.mapOrderToDomain(dto.order),
        client = OrderDetailsClientModel(dto.client.name, dto.client.surname, dto.client.phone),
        index = dto.index
    )

}
