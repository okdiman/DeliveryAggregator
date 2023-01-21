package root.presentation.mapper

import orderdetails.domain.model.OrderDetailsStatusProgress
import root.domain.model.RouteOrderModel
import root.presentation.compose.model.RouteOrderStatusUiModel
import root.presentation.compose.model.RouteOrderUiModel
import utils.CommonConstants.Helpers.COMMA

class RouteUiMapper {
    fun map(data: List<RouteOrderModel>) = data.map {
        RouteOrderUiModel(
            id = it.order.id,
            //FIXME корректно отображать дату!!
            arrivalDate = buildString { append(it.order.arrivalTime) },
            status = mapStatusToUi(it.order.status),
            departureAddress = buildString {
                append(it.order.address.city + COMMA + it.order.address.street + COMMA + it.order.address.house)
            },
            deliveryAddress = it.order.storage.address
        )
    }

    private fun mapStatusToUi(model: OrderDetailsStatusProgress?) =
        when (model) {
            OrderDetailsStatusProgress.ACTIVE -> RouteOrderStatusUiModel.ACTIVE
            OrderDetailsStatusProgress.IN_PROGRESS -> RouteOrderStatusUiModel.IN_PROGRESS
            OrderDetailsStatusProgress.DONE -> RouteOrderStatusUiModel.DONE
            else -> null
        }
}