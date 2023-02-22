package orderdetails.deliverystate.domain

import orderdetails.deliverystate.domain.model.DeliveryStateRequestModel
import root.domain.RouteRepository

class ConfirmDeliveryStateUseCase(
    private val repository: RouteRepository
) {
    suspend operator fun invoke(id: Long, model: DeliveryStateRequestModel) {
        repository.confirmDeliveryState(id, model)
    }
}