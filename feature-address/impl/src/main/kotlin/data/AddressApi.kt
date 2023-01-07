package data

import data.model.request.AddressSuggestRequest
import data.model.request.AuthAddressSuggestRequest
import data.model.response.AddressSuggestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AddressApi {
    @POST("/auth/addresses/suggest")
    suspend fun getAuthAddressesByQuery(
        @Body request: AuthAddressSuggestRequest
    ): ArrayList<AddressSuggestDto>

    @POST("/api/users/addresses/suggest")
    suspend fun getAddressesByQuery(
        @Body request: AddressSuggestRequest
    ): ArrayList<AddressSuggestDto>
}