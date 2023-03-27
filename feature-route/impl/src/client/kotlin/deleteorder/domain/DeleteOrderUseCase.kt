package deleteorder.domain

import root.domain.RouteRepository

class DeleteOrderUseCase(
    private val repository: RouteRepository
) {
    suspend operator fun invoke(id: Long) = repository.deleteOrder(id)
}