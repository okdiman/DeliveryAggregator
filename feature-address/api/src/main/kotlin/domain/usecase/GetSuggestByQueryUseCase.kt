package domain.usecase

import domain.model.AddressSuggestModel
import domain.model.request.AddressSuggestRequestModel

interface GetSuggestByQueryUseCase :
    suspend (AddressSuggestRequestModel) -> List<AddressSuggestModel>