package root.domain.usecase

import root.domain.RouteRepository

class GetOrdersUseCase(
    private val repository: RouteRepository
) {
    suspend operator fun invoke() = repository.getOrderRequests()
}