package root.data

import neworder.root.data.model.request.NewOrderRequest
import neworder.root.data.model.response.PriceDto
import neworder.storage.data.StorageWrapperDto
import orderchanges.data.model.response.OrderChangesDto
import orderdetails.root.data.model.extras.ExtrasDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import root.data.model.response.OrdersDto
import root.domain.model.RouteOrderDto

interface OrdersApi {
    @GET("/api/clients/requests")
    suspend fun getClientOrders(): OrdersDto

    @GET("/api/contractors/requests/{id}")
    suspend fun getOrderDetails(
        @Path("id") id: Long
    ): RouteOrderDto

    @GET("api/users/extras")
    suspend fun getExtras(): ExtrasDto

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
}
