package root.data.model.response

import kotlinx.serialization.Serializable
import utils.serializers.BigDecimalAsDoubleSerializer
import java.math.BigDecimal

@Serializable
class OrderExtrasDto(
    val id: Long,
    val name: String,
    @Serializable(with = BigDecimalAsDoubleSerializer::class)
    val price: BigDecimal,
    val priceDescription: OrderPriceDescriptionDto,
    val count: Int = 0
)