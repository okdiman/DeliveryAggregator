package orderdetails.loadingstate.domain

import orderdetails.loadingstate.domain.model.LoadingStateRequestModel
import root.domain.RouteRepository

class ConfirmLoadingStateUseCase(
    private val repository: RouteRepository
) {
    suspend operator fun invoke(id: Long, model: LoadingStateRequestModel) {
        repository.confirmLoadingState(id, model)
    }
}