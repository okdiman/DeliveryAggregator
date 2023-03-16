package orderdetails.root.presentation.mapper

import orderdetails.root.presentation.compose.model.OrderDetailsUiModel
import root.domain.model.RouteOrderModel
import utils.CommonConstants.Helpers.SPACER
import utils.ext.DateFormats
import utils.ext.toString

class OrderDetailsMapper {
    fun map(model: RouteOrderModel, index: Int): OrderDetailsUiModel {
        return OrderDetailsUiModel(
            id = model.details.id,
            index = (index + 1).toString(),
            clientName = buildString {
                append(model.client.name + SPACER + model.client.surname)
            },
            clientPhone = model.client.phone ?: "",
            deliveryDate = model.details.arrivalDay.toString(DateFormats.DOT_DAY_FORMAT),
            deliveryTime = model.details.arrivalTime,
            palletCount = model.details.pallets.toString(),
            deliveryAddress = model.details.storage.address,
            status = model.details.status
        )
    }
}