package root.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import orderdetails.root.data.model.OrderDetailsDto
import orderdetails.root.data.model.details.OrderDetailsClientDto

@Serializable
data class RouteOrderDto(
    @SerialName("request")
    val order: OrderDetailsDto,
    @SerialName("index")
    val index: Int,
    @SerialName("client")
    val client: OrderDetailsClientDto
)
