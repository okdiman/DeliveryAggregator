package root.presentation.mapper

import root.domain.model.RouteOrderModel
import root.domain.model.status.OrderStatusProgress
import root.presentation.compose.model.RouteOrderStatusUiModel
import root.presentation.compose.model.RouteOrderUiModel
import utils.CommonConstants.Helpers.COMMA
import utils.ext.DateFormats.FULL_DISPLAYED_DAY_MONTH_FORMATTER
import utils.ext.toString

class RouteUiMapper {
    fun map(data: List<RouteOrderModel>) = data.map {
        RouteOrderUiModel(
            id = it.details.id,
            arrivalDate = buildString {
                append(it.details.arrivalDay.toString(FULL_DISPLAYED_DAY_MONTH_FORMATTER))
                append(COMMA + it.details.arrivalTime.lowercase())
            },
            status = mapStatusToUi(it.details.status),
            departureAddress = buildString {
                append(it.details.address.city + COMMA + it.details.address.street + COMMA + it.details.address.house)
            },
            deliveryAddress = it.details.storage.address
        )
    }

    private fun mapStatusToUi(model: OrderStatusProgress?) =
        when (model) {
            OrderStatusProgress.DELIVERY, OrderStatusProgress.CHANGED -> RouteOrderStatusUiModel.IN_PROGRESS
            OrderStatusProgress.ASSIGNED -> RouteOrderStatusUiModel.ACTIVE
            OrderStatusProgress.DONE -> RouteOrderStatusUiModel.DONE
            else -> null
        }
}