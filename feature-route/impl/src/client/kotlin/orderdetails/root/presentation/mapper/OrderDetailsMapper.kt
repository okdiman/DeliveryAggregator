package orderdetails.root.presentation.mapper

import orderdetails.root.domain.model.OrderDeliveryModel
import orderdetails.root.domain.model.OrderLoadModel
import orderdetails.root.presentation.compose.model.OrderDeliveryUiModel
import orderdetails.root.presentation.compose.model.OrderDetailsUiModel
import orderdetails.root.presentation.compose.model.OrderLoadUiModel
import root.domain.model.OrderModel
import utils.CommonConstants.Helpers.SPACER
import utils.ext.DateFormats
import utils.ext.toString

class OrderDetailsMapper {
    fun map(model: OrderModel): OrderDetailsUiModel {
        return OrderDetailsUiModel(
            id = model.details.id,
            contractorName = model.contractor?.let { contractor ->
                buildString {
                    append(contractor.name + SPACER + contractor.surname)
                }
            },
            contractorPhone = model.contractor?.phone,
            deliveryDate = model.details.delivery?.deliveryDateTime?.toString(DateFormats.DOT_DAY_FORMAT),
            arrivalTime = model.details.arrivalTime,
            palletCount = model.details.pallets.toString(),
            deliveryAddress = model.details.storage.address,
            status = model.details.status,
            load = model.details.load?.let(::mapLoadToUi),
            delivery = model.details.delivery?.let(::mapDeliveryToUi),
            isPaid = model.details.isPaid,
            price = model.details.price
        )
    }

    private fun mapLoadToUi(model: OrderLoadModel) = OrderLoadUiModel(
        loadDateTime = model.loadDateTime.toString(DateFormats.FULL_DISPLAYED_DATE_TIME_FORMATTER),
        imageUrl = model.images.first(),
    )

    private fun mapDeliveryToUi(model: OrderDeliveryModel) = OrderDeliveryUiModel(
        deliveryDateTime = model.deliveryDateTime.toString(DateFormats.FULL_DISPLAYED_DATE_TIME_FORMATTER),
        imageUrl = model.images.first(),
    )
}
