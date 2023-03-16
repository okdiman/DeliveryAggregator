package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrdersDto(
    @SerialName("ContractorRequests")
    val orders: List<OrderDto>?,
)
