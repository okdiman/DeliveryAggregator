package root.presentation.mapper

import root.domain.OrderStatusProgress
import root.domain.model.OrderModel
import root.presentation.compose.model.RouteOrderStatusUiModel
import root.presentation.compose.model.RouteOrderUiModel
import utils.CommonConstants.Helpers.COMMA

class RouteUiMapper {
    fun map(data: List<OrderModel>) = data.map {
        RouteOrderUiModel(
            id = it.id,
            arrivalDate = buildString { append(it.arrivalTime) },
            status = mapStatusToUi(it.status),
            departureAddress = buildString {
                append(it.address.city + COMMA + it.address.street + COMMA + it.address.house)
            },
            deliveryAddress = it.storage.address
        )
    }

    private fun mapStatusToUi(model: OrderStatusProgress?) =
        when (model) {
            OrderStatusProgress.ACTIVE -> RouteOrderStatusUiModel.ACTIVE
            OrderStatusProgress.INPROGRESS -> RouteOrderStatusUiModel.INPROGRESS
            OrderStatusProgress.DONE -> RouteOrderStatusUiModel.DONE
            else -> null
        }
}