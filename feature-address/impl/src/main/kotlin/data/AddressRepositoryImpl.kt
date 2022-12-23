package data

import data.mapper.AddressSuggestMapper
import data.model.request.AddressSuggestRequest
import domain.AddressRepository
import domain.model.AddressSuggestModel
import domain.model.AddressSuggestRequestModel

class AddressRepositoryImpl(
    private val api: AddressApi,
    private val mapper: AddressSuggestMapper
) : AddressRepository {
    override suspend fun getRegistrationSuggests(model: AddressSuggestRequestModel): List<AddressSuggestModel> {
        val response = api.getAddressesForRegistrationByQuery(
            AddressSuggestRequest(
                code = model.code,
                phone = model.phone,
                query = model.query
            )
        )
        return mapper.map(response)
    }
}