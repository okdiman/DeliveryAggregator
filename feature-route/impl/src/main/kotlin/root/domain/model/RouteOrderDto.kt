package root.domain.model

import com.google.gson.annotations.SerializedName
import orderdetails.root.data.model.OrderDetailsDto
import orderdetails.root.data.model.details.OrderDetailsClientDto

data class RouteOrderDto(
    @SerializedName("request")
    val order: OrderDetailsDto,
    @SerializedName("index")
    val index: Int,
    @SerializedName("client")
    val client: OrderDetailsClientDto
)