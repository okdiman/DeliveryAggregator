package orderdetails.root.domain

import root.domain.RouteRepository

class GetClientOrderDetailsUseCase(
    private val repository: RouteRepository
) {
    suspend operator fun invoke(id: Long) = repository.getClientOrderDetails(id)
}
