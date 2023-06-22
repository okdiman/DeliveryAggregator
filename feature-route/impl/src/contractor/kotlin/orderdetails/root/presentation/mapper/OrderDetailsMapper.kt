package orderdetails.root.presentation.mapper

import domain.usecase.GetMaskedPhoneUseCase
import orderdetails.root.presentation.compose.model.OrderDetailsUiModel
import root.domain.model.OrderAddressModel
import root.domain.model.RouteOrderModel
import utils.CommonConstants.Helpers.COMMA
import utils.CommonConstants.Helpers.SPACER
import utils.ext.DateFormats
import utils.ext.toString

class OrderDetailsMapper (private val getMaskedPhone: GetMaskedPhoneUseCase) {
    fun map(model: RouteOrderModel, index: Int): OrderDetailsUiModel {
        return OrderDetailsUiModel(
            id = model.details.id,
            index = (index + 1).toString(),
            clientName = buildString {
                append(model.client.name + SPACER + model.client.surname)
            },
            clientPhone = model.client.phone?.let { getMaskedPhone(it) } ?: "",
            deliveryDate = model.details.arrivalDay.toString(DateFormats.DOT_DAY_FORMAT),
            deliveryTime = model.details.arrivalTime,
            palletCount = model.details.pallets.toString(),
            deliveryAddress = mapAddress(model.details.address),
            storageAddress = model.details.storage.address,
            status = model.details.status,
            cargoType = model.details.cargoType
        )
    }

    private fun mapAddress(address: OrderAddressModel) = buildString {
        append(address.city + COMMA + address.street + COMMA + address.house)
    }
}