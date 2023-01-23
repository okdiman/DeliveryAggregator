package root.data.model

import com.google.gson.annotations.SerializedName
import orderdetails.data.model.details.OrderDetailsClientDto
import orderdetails.data.model.OrderDetailsDto

data class RouteOrderDto(
    @SerializedName("request")
    val order: OrderDetailsDto,
    @SerializedName("index")
    val index: Int,
    @SerializedName("client")
    val client: OrderDetailsClientDto
)