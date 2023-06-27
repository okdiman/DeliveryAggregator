package root.data

import neworder.root.data.model.request.NewOrderRequest
import neworder.root.data.model.response.PriceDto
import neworder.storage.data.StorageWrapperDto
import orderchanges.data.model.response.OrderChangesDto
import orderdetails.root.data.model.extras.ExtrasListDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import root.data.model.response.OrderRequestDto
import root.data.model.response.OrdersDto
import root.data.model.response.RouteOrderDto

interface OrdersApi {
    @GET("/api/clients/requests")
    suspend fun getClientOrders(): OrdersDto

    @GET("/api/contractors/requests/{id}")
    suspend fun getOrderDetails(
        @Path("id") id: Long
    ): RouteOrderDto

    @GET("/api/clients/requests/{id}")
    suspend fun getClientOrderDetails(
        @Path("id") id: Long
    ): OrderRequestDto

    @GET("api/users/extras")
    suspend fun getExtras(): ExtrasListDto

    @POST("api/clients/requests/price")
    suspend fun getOrderPrice(
        @Body request: NewOrderRequest
    ): PriceDto

    @POST("api/clients/requests")
    suspend fun createOrder(
        @Body request: NewOrderRequest
    )

    @GET("api/clients/requests/{id}/changed")
    suspend fun getOrderChanges(
        @Path("id") id: Long,
    ): OrderChangesDto

    @GET("api/clients/requests/{id}/accept")
    suspend fun confirmOrderChanges(
        @Path("id") id: Long,
    )

    @GET("api/clients/storages")
    suspend fun getStorages(): StorageWrapperDto

    @DELETE("api/clients/requests/{id}")
    suspend fun deleteOrder(
        @Path("id") id: Long
    )
}