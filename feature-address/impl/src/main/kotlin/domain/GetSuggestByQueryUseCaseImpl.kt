package domain

import domain.model.AddressSuggestRequestModel
import domain.usecase.GetSuggestByQueryUseCase

class GetSuggestByQueryUseCaseImpl(
    private val repository: AddressRepository
) : GetSuggestByQueryUseCase {
    override suspend fun invoke(model: AddressSuggestRequestModel) =
        repository.getRegistrationSuggests(model)
}