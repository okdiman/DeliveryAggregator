package neworder.root.data.mapper

import extras.data.ExtrasDto
import neworder.root.data.model.request.NewOrderRequest
import root.domain.model.NewOrderModel

class NewOrderMapper {
    fun map(model: NewOrderModel) = NewOrderRequest(
        addressId = model.addressId,
        arrivalDay = model.arrivalDay,
        arrivalTime = model.arrivalTime,
        cargoType = model.cargoType,
        boxes = model.boxes,
        comment = model.comment,
        marketplaceId = model.marketplaceId,
        pallets = model.pallets,
        storageId = model.storageId,
        weight = model.weight,
        extras = model.extras.map { ExtrasDto(it.id, it.quantity) }
    )
}