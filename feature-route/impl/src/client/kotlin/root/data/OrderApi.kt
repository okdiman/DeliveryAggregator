package root.data

import retrofit2.http.GET
import root.data.model.response.OrderRequestsDto

interface OrderApi {

    @GET("/api/clients/requests")
    suspend fun getOrderRequests(): OrderRequestsDto
}
