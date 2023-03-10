package orderchanges.data.mapper

import cargotype.domain.model.CargoType
import orderchanges.data.model.response.OrderChangedDto
import orderchanges.domain.model.OrderChangedModel
import orderchanges.domain.model.OrderChangedValuesModel
import root.data.mapper.RouteOrderMapper

class OrderChangesMapper(
    private val orderMapper: RouteOrderMapper
) {
    fun mapChangesToDomain(dto: OrderChangedDto) = OrderChangedModel(
        orderId = dto.order.id,
        before = OrderChangedValuesModel(
            boxes = dto.order.boxes,
            pallets = dto.order.pallets,
            cargoType = CargoType.values().first { it.text == dto.order.cargoType },
            price = dto.order.price,
            extras = dto.order.extras.orEmpty().map(orderMapper::mapExtrasToDomain),
        ),
        after = OrderChangedValuesModel(
            boxes = dto.changedOrder.boxes,
            pallets = dto.changedOrder.pallets,
            cargoType = CargoType.values().first { it.text == dto.changedOrder.cargoType },
            price = dto.changedOrder.price,
            extras = dto.changedOrder.extras.orEmpty().map(orderMapper::mapExtrasToDomain)
        )
    )

}
