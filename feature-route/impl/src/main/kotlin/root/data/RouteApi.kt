package root.data

import retrofit2.http.GET
import root.data.model.RouteDto

interface RouteApi {

    @GET("/api/contractors/routes/active")
    suspend fun getActiveRoute(): RouteDto
}