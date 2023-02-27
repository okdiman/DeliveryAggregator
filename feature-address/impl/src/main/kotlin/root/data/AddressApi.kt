package root.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import root.data.model.AddressRequest
import root.data.model.request.AddressAuthSuggestRequest
import root.data.model.request.AddressSuggestRequest
import root.data.model.response.AddressSuggestDto
import root.data.model.response.AddressesDto

interface AddressApi {
    @POST("/auth/addresses/suggest")
    suspend fun getAuthAddressesByQuery(
        @Body request: AddressAuthSuggestRequest
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