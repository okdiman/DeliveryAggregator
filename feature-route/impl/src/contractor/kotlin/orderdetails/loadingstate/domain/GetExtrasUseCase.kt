package orderdetails.loadingstate.domain

import root.domain.RouteRepository

class GetExtrasUseCase(
    private val repository: RouteRepository
) {
    suspend operator fun invoke() = repository.getExtras()
}