package orderchanges.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import root.data.model.response.OrderExtrasDto

@Serializable
class OrderChangesDto(
    @SerialName("requestId")
    val orderId: Long,
    val boxes: Int,
    val pallets: Int,
    val cargoType: String,
    val price: Int,
    val extras: List<OrderExtrasDto>,
    val images: List<String>,
)
