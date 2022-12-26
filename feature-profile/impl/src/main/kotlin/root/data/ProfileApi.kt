package root.data

import data.model.request.ProfileRequest
import data.model.response.ProfileResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT

interface ProfileApi {
    @GET("/api/contractors/profile")
    suspend fun getProfile(): ProfileResponse

    @PUT("/api/contractors/profile")
    suspend fun updateProfile(@Body request: ProfileRequest)

    @DELETE("/api/contractors/profile")
    suspend fun deleteProfile()
}