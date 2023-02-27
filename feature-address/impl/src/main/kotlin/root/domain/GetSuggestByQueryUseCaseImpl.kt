package root.domain

import domain.AddressRepository
import domain.model.request.AddressSuggestRequestModel
import domain.usecase.GetSuggestByQueryUseCase

class GetSuggestByQueryUseCaseImpl(
    private val repository: AddressRepository
) : GetSuggestByQueryUseCase {
    override suspend operator fun invoke(model: AddressSuggestRequestModel) =
        repository.getSuggests(model)
}