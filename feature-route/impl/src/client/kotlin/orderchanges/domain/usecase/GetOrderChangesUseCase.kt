package orderchanges.domain.usecase

import root.domain.RouteRepository

class GetOrderChangesUseCase(
    private val repository: RouteRepository,
) {

    suspend operator fun invoke(id: Long) = repository.getOrderChanges(id)
}
