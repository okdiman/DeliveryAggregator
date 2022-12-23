package domain

import domain.model.AddressSuggestModel
import domain.model.AddressSuggestRequestModel

interface AddressRepository {
    suspend fun getRegistrationSuggests(model: AddressSuggestRequestModel): List<AddressSuggestModel>
}