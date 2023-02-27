package neworder.storage.domain

import root.domain.RouteRepository

class GetStoragesUseCase(
    private val repository: RouteRepository
) {
    suspend operator fun invoke() = repository.getStorages()
}