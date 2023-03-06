package root.data.model.response

import com.google.gson.annotations.SerializedName
import orderdetails.root.data.model.OrderDetailsDto

data class OrdersDto(
    @SerializedName("Requests")
    val orders: List<OrderDetailsDto>?
)