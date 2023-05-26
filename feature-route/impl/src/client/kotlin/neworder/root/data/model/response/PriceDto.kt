package neworder.root.data.model.response

import kotlinx.serialization.Serializable
import utils.serializers.BigDecimalAsDoubleSerializer
import java.math.BigDecimal

@Serializable
class PriceDto(
    @Serializable(with = BigDecimalAsDoubleSerializer::class)
    val price: BigDecimal
)
