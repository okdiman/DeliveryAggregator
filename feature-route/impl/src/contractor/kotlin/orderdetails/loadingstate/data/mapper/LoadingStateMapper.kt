package orderdetails.loadingstate.data.mapper

import extras.data.ExtrasDto
import orderdetails.loadingstate.data.model.LoadingStateRequest
import orderdetails.loadingstate.domain.model.LoadingStateRequestModel

class LoadingStateMapper {
    fun map(model: LoadingStateRequestModel) = LoadingStateRequest(
        boxes = model.boxes,
        pallets = model.pallets,
        images = model.images,
        extras = model.extras.map { ExtrasDto(it.id, it.quantity) },
        cargoType = model.cargoType
    )
}