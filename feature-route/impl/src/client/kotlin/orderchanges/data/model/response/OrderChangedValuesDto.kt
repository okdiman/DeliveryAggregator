package orderchanges.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import root.data.model.response.OrderExtrasDto
import utils.serializers.BigDecimalAsDoubleSerializer
import java.math.BigDecimal

@Serializable
class OrderChangedValuesDto(
    @SerialName("requestId")
    val orderId: Long,
    val boxes: Int,
    val pallets: Int,
    val cargoType: String,
    @Serializable(with = BigDecimalAsDoubleSerializer::class)
    val price: BigDecimal,
    val extras: List<OrderExtrasDto>?,
    val images: List<String>?,
)
