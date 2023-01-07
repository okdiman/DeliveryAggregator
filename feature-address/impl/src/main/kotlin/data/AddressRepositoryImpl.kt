package data

import data.mapper.AddressSuggestMapper
import data.model.request.AddressSuggestRequest
import data.model.request.AuthAddressSuggestRequest
import domain.AddressRepository
import domain.model.AddressSuggestModel
import domain.model.AddressSuggestRequestModel
import domain.model.AuthAddressSuggestRequestModel

class AddressRepositoryImpl(
    private val api: AddressApi,
    private val mapper: AddressSuggestMapper
) : AddressRepository {
    override suspend fun getAuthSuggests(model: AuthAddressSuggestRequestModel): List<AddressSuggestModel> {
        val response = api.getAuthAddressesByQuery(
            AuthAddressSuggestRequest(
                code = model.code,
                phone = model.phone,
                query = model.query
            )
        )
        return mapper.map(response)
    }

    override suspend fun getSuggests(model: AddressSuggestRequestModel): List<AddressSuggestModel> {
        val response = api.getAddressesByQuery(AddressSuggestRequest(model.query))
        return mapper.map(response)
    }
}