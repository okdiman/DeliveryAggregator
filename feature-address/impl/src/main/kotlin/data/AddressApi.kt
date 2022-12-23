package data

import data.model.request.AddressSuggestRequest
import data.model.response.AddressSuggestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AddressApi {
    @POST("/auth/addresses/suggest")
    suspend fun getAddressesForRegistrationByQuery(
        @Body request: AddressSuggestRequest
    ): ArrayList<AddressSuggestDto>
}