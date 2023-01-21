package domain

import domain.model.request.AddressAuthSuggestRequestModel

class GetAuthSuggestByQueryUseCase(private val repository: AddressRepository) {
    suspend operator fun invoke(model: AddressAuthSuggestRequestModel) =
        repository.getAuthSuggests(model)
}