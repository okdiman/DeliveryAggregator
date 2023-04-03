package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RouteDto(
    val id: Long,
    @SerialName("clientRequest")
    val orders: List<RouteOrderDto>,
    @SerialName("status")
    val status: String,
    @SerialName("totalPrice")
    val totalPrice: Int,
    @SerialName("distance")
    val distance: Double
)
