package domain.usecase

import domain.model.AddressSuggestModel
import domain.model.AddressSuggestRequestModel

interface GetSuggestByQueryUseCase :
    suspend (AddressSuggestRequestModel) -> List<AddressSuggestModel>