package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import utils.serializers.BigDecimalAsDoubleSerializer
import java.math.BigDecimal

@Serializable
class RouteDto(
    val id: Long,
    @SerialName("clientRequest")
    val orders: List<RouteOrderDto>,
    @SerialName("status")
    val status: String,
    @SerialName("totalPrice")
    @Serializable(with = BigDecimalAsDoubleSerializer::class)
    val totalPrice: BigDecimal,
    @SerialName("distance")
    val distance: Double
)
