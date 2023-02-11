package root.data

import retrofit2.http.GET
import retrofit2.http.Path
import root.data.model.RouteDto
import root.data.model.RouteOrderDto

interface RouteApi {

    @GET("/api/contractors/routes/active")
    suspend fun getActiveRoute(): RouteDto

    @GET("/api/contractors/requests/{id}")
    suspend fun getOrderDetails(
        @Path("id") id: Long
    ): RouteOrderDto

    @GET("api/contractors/routes/{id}/accept")
    suspend fun acceptRoute(
        @Path("id") id: Long
    )
}