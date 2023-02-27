package neworder.root.domain

import root.domain.RouteRepository
import root.domain.model.NewOrderModel

class NewOrderInteractor(
    private val repository: RouteRepository
) {
    suspend fun getNewOrderPrice(model: NewOrderModel) = repository.getNewOrderPrice(model)
    suspend fun createNewOrder(model: NewOrderModel) {
        repository.createNewOrder(model)
    }
}