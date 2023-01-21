package data

import data.mapper.AddressMapper
import data.mapper.AddressSuggestMapper
import data.model.request.AddressSuggestRequest
import data.model.request.AddressAuthSuggestRequest
import domain.AddressRepository
import domain.model.AddressModel
import domain.model.AddressSuggestModel
import domain.model.request.AddressSuggestRequestModel
import domain.model.request.AddressAuthSuggestRequestModel

class AddressRepositoryImpl(
    private val api: AddressApi,
    private val mapper: AddressMapper,
    private val suggestMapper: AddressSuggestMapper
) : AddressRepository {
    override suspend fun getUserAddresses(): List<AddressModel> {
        val addresses = api.getUserAddresses()
        return mapper.mapToDomain(addresses)
    }

    override suspend fun addUserAddress(addressModel: AddressModel) {
        api.addUserAddress(mapper.mapToData(addressModel))
    }

    override suspend fun updateUserAddress(addressModel: AddressModel) {
        api.updateUserAddress(addressModel.id, mapper.mapToData(addressModel))
    }

    override suspend fun getAuthSuggests(model: AddressAuthSuggestRequestModel): List<AddressSuggestModel> {
        val response = api.getAuthAddressesByQuery(
            AddressAuthSuggestRequest(
                code = model.code,
                phone = model.phone,
                query = model.query
            )
        )
        return suggestMapper.map(response)
    }

    override suspend fun getSuggests(model: AddressSuggestRequestModel): List<AddressSuggestModel> {
        val response = api.getAddressesByQuery(AddressSuggestRequest(model.query))
        return suggestMapper.map(response)
    }
}