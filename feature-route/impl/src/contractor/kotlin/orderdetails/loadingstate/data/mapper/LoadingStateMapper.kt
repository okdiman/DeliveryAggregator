package orderdetails.loadingstate.data.mapper

import orderdetails.loadingstate.data.model.LoadingStateRequest
import orderdetails.loadingstate.domain.model.LoadingStateRequestModel

class LoadingStateMapper {
    fun map(model: LoadingStateRequestModel) = LoadingStateRequest(
        boxes = model.boxes,
        pallets = model.pallets,
        images = model.images,
        extras = model.extras,
        cargoType = model.cargoType
    )
}