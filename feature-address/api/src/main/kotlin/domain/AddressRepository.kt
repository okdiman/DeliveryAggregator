package domain

import domain.model.AddressModel
import domain.model.AddressSuggestModel
import domain.model.request.AddressSuggestRequestModel
import domain.model.request.AuthAddressSuggestRequestModel

interface AddressRepository {
    suspend fun getUserAddresses(): List<AddressModel>
    suspend fun addUserAddress(addressModel: AddressModel)
    suspend fun updateUserAddress(addressModel: AddressModel)

    /**
     * Первый метод получения саджестов используется при регистрации, так как там нет токена,
     * то передаются телефон и одноразовый код. В авторизованной зоне использцется второй метод
     */
    suspend fun getAuthSuggests(model: AuthAddressSuggestRequestModel): List<AddressSuggestModel>
    suspend fun getSuggests(model: AddressSuggestRequestModel): List<AddressSuggestModel>
}