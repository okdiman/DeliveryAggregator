package root.presentation.mapper

import orderdetails.domain.model.status.OrderDetailsStatusProgress
import root.domain.model.RouteOrderModel
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
                append(COMMA + it.details.arrivalTime.replaceFirstChar { ARRIVAL_DATE_PREFIX })
            },
            status = mapStatusToUi(it.details.status),
            departureAddress = buildString {
                append(it.details.address.city + COMMA + it.details.address.street + COMMA + it.details.address.house)
            },
            deliveryAddress = it.details.storage.address
        )
    }

    private fun mapStatusToUi(model: OrderDetailsStatusProgress?) =
        when (model) {
            OrderDetailsStatusProgress.DELIVERY, OrderDetailsStatusProgress.LOADING ->
                RouteOrderStatusUiModel.IN_PROGRESS
            OrderDetailsStatusProgress.ACTIVE -> RouteOrderStatusUiModel.ACTIVE
            OrderDetailsStatusProgress.DONE -> RouteOrderStatusUiModel.DONE
            else -> null
        }

    private companion object {
        const val ARRIVAL_DATE_PREFIX = "с"
    }
}