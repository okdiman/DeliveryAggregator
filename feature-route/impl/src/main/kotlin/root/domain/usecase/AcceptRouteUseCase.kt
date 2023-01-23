package root.domain.usecase

import root.domain.RouteRepository

class AcceptRouteUseCase(
    private val repository: RouteRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.acceptRoute(id)
    }
}