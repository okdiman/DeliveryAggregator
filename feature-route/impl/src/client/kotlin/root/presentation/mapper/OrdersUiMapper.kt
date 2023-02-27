package root.presentation.mapper

import orderdetails.root.domain.model.OrderDetailsModel
import root.domain.model.status.OrderStatusProgress
import root.domain.model.status.OrderStatusProgress.ASSIGNED
import root.domain.model.status.OrderStatusProgress.CREATED
import root.domain.model.status.OrderStatusProgress.DELIVERY
import root.domain.model.status.OrderStatusProgress.DONE
import root.presentation.compose.model.OrderClientUiModel
import root.presentation.compose.model.OrderStatusCategoryUiModel
import root.presentation.compose.model.OrderStatusUiModel
import utils.CommonConstants.Helpers.COMMA

class OrdersUiMapper {

    fun map(models: List<OrderDetailsModel>) = models.map {
        OrderClientUiModel(
            id = it.id,
            arrivalDate = it.arrivalTime,
            status = mapStatusToUi(it.status),
            statusCategory = mapStatusToStatusCategoryUi(it.status),
            departureAddress = buildString {
                append(it.address.city + COMMA + it.address.street + COMMA + it.address.house)
            },
            deliveryAddress = it.storage.address
        )
    }

    private fun mapStatusToStatusCategoryUi(model: OrderStatusProgress): OrderStatusCategoryUiModel =
        when (model) {
            //FIXME Пока нельзя получить статус PAID
            CREATED, ASSIGNED, DELIVERY -> OrderStatusCategoryUiModel.ACTIVE
            DONE -> OrderStatusCategoryUiModel.DONE
        }

    private fun mapStatusToUi(model: OrderStatusProgress): OrderStatusUiModel =
        when (model) {
            CREATED -> OrderStatusUiModel.CREATED
            ASSIGNED -> OrderStatusUiModel.ASSIGNED
            DELIVERY -> OrderStatusUiModel.DELIVERY
            DONE -> OrderStatusUiModel.DONE
        }
}