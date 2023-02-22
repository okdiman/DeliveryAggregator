package root.domain.usecase

import root.domain.RouteRepository

class GetActiveRouteUseCase(
    private val repository: RouteRepository
) {
    suspend operator fun invoke() = repository.getActiveRoute()
}