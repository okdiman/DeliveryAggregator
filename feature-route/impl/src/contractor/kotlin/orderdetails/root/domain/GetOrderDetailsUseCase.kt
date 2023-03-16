package orderdetails.root.domain

import root.domain.RouteRepository

class GetOrderDetailsUseCase(
    private val repository: RouteRepository
) {
    suspend operator fun invoke(id: Long) = repository.getOrderDetails(id)
}