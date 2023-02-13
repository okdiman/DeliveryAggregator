package root.data

import orderdetails.deliverystate.data.model.DeliveryStateRequest
import orderdetails.loadingstate.data.model.LoadingStateRequest
import orderdetails.root.data.model.extras.ExtrasDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import root.data.model.response.RouteDto
import root.data.model.response.RouteOrderDto

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

    @PUT("api/contractors/requests/{id}")
    suspend fun confirmLoadingState(
        @Path("id") id: Long,
        @Body request: LoadingStateRequest
    )

    @POST("api/contractors/requests/{id}")
    suspend fun confirmDeliveryState(
        @Path("id") id: Long,
        @Body request: DeliveryStateRequest
    )

    @GET("api/users/extras")
    suspend fun getExtras(): ExtrasDto
}