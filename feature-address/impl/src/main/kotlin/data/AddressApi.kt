package data

import data.model.request.AddressRequest
import data.model.request.AddressSuggestRequest
import data.model.request.AuthAddressSuggestRequest
import data.model.response.AddressSuggestDto
import data.model.response.AddressesDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AddressApi {
    @POST("/auth/addresses/suggest")
    suspend fun getAuthAddressesByQuery(
        @Body request: AuthAddressSuggestRequest
    ): ArrayList<AddressSuggestDto>

    @POST("/api/users/addresses/suggest")
    suspend fun getAddressesByQuery(
        @Body request: AddressSuggestRequest
    ): ArrayList<AddressSuggestDto>

    @GET("/api/users/addresses")
    suspend fun getUserAddresses(): AddressesDto

    @POST("/api/users/addresses")
    suspend fun addUserAddress(
        @Body address: AddressRequest
    )

    @PUT("/api/users/addresses/{id}")
    suspend fun updateUserAddress(
        @Path("id") addressId: String,
        @Body address: AddressRequest
    )
}