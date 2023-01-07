package domain

import domain.model.AddressSuggestModel
import domain.model.AddressSuggestRequestModel
import domain.model.AuthAddressSuggestRequestModel

interface AddressRepository {
    /**
     * Первый метод получения саджестов используется при регистрации, так как там нет токена,
     * то передаются телефон и одноразовый код. В авторизованной зоне использцется второй метод
     */
    suspend fun getAuthSuggests(model: AuthAddressSuggestRequestModel): List<AddressSuggestModel>
    suspend fun getSuggests(model: AddressSuggestRequestModel): List<AddressSuggestModel>
}