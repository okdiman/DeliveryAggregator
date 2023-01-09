package domain

import domain.model.request.AuthAddressSuggestRequestModel

class GetAuthSuggestByQueryUseCase(private val repository: AddressRepository) {
    suspend operator fun invoke(model: AuthAddressSuggestRequestModel) =
        repository.getAuthSuggests(model)
}