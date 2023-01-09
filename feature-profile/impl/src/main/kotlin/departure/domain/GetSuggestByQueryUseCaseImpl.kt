package departure.domain

import domain.AddressRepository
import domain.model.AddressSuggestRequestModel
import domain.usecase.GetSuggestByQueryUseCase

class GetSuggestByQueryUseCaseImpl(
    private val repository: AddressRepository
) : GetSuggestByQueryUseCase {
    override suspend operator fun invoke(model: AddressSuggestRequestModel) =
        repository.getSuggests(model)
}