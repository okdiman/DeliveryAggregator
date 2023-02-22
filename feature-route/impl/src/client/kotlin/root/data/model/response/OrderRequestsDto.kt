package root.data.model.response

import com.google.gson.annotations.SerializedName

data class OrderRequestsDto(
    @SerializedName("Requests")
    val orders: List<OrderDto>?
)
