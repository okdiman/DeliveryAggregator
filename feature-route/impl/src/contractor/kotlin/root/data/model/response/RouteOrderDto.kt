package root.data.model.response

import com.google.gson.annotations.SerializedName
import orderdetails.root.data.model.details.OrderDetailsClientDto

data class RouteOrderDto(
    @SerializedName("request")
    val order: OrderDto,
    @SerializedName("index")
    val index: Int,
    @SerializedName("client")
    val client: OrderDetailsClientDto
)
