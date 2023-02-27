package root.domain

import domain.AddressRepository
import domain.model.request.AddressAuthSuggestRequestModel
import domain.usecase.GetAuthSuggestByQueryUseCase

class GetAuthSuggestByQueryUseCaseImpl(private val repository: AddressRepository) : GetAuthSuggestByQueryUseCase {
    override suspend fun invoke(model: AddressAuthSuggestRequestModel) = repository.getAuthSuggests(model)
}