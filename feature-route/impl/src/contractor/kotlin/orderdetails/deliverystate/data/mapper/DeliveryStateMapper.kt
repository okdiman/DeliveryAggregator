package orderdetails.deliverystate.data.mapper

import orderdetails.deliverystate.data.model.DeliveryStateRequest
import orderdetails.deliverystate.domain.model.DeliveryStateRequestModel

class DeliveryStateMapper {
    fun map(model: DeliveryStateRequestModel) = DeliveryStateRequest(
        images = model.images,
        isProblem = model.isProblem,
        comment = model.comment
    )
}