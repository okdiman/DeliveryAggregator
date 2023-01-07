package domain.usecase

import domain.model.AddressSuggestModel
import domain.model.AddressSuggestRequestModel

interface GetSuggestByQueryUseCase {
    suspend operator fun invoke(model: AddressSuggestRequestModel): List<AddressSuggestModel>
}