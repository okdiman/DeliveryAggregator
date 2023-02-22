package root.domain.usecase

import root.domain.OrderRequestsRepository

class GetOrderRequestsUseCase(
    private val repository: OrderRequestsRepository
) {
    suspend operator fun invoke() = repository.getOrderRequests()
}
