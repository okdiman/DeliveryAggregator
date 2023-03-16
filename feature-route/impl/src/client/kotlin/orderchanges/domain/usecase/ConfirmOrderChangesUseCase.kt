package orderchanges.domain.usecase

import root.domain.RouteRepository

class ConfirmOrderChangesUseCase(
    private val repository: RouteRepository,
) {

    suspend operator fun invoke(id: Long) = repository.confirmOrderChanges(id)
}
