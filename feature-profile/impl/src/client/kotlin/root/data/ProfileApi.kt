package root.data

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import root.data.model.request.ProfileRequest
import root.data.model.response.ProfileResponse

interface ProfileApi {
    @GET("/api/clients/profile")
    suspend fun getProfile(): ProfileResponse

    @PUT("/api/clients/profile")
    suspend fun updateProfile(@Body request: ProfileRequest)

    @DELETE("/api/clients/profile")
    suspend fun deleteProfile()
}