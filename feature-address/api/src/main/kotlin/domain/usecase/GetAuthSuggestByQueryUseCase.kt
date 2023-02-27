package domain.usecase

import domain.model.AddressSuggestModel
import domain.model.request.AddressAuthSuggestRequestModel

interface GetAuthSuggestByQueryUseCase : suspend (AddressAuthSuggestRequestModel) -> List<AddressSuggestModel>