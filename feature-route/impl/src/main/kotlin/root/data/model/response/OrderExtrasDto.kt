package root.data.model.response

import kotlinx.serialization.Serializable

@Serializable
class OrderExtrasDto(
    val id: Long,
    val name: String,
    val price: Int,
    val priceDescription: OrderPriceDescriptionDto,
    val count: Int = 0
)