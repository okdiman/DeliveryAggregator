package root.domain.model.extras

import java.math.BigDecimal

data class OrderExtrasModel(
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val priceDescription: OrderPriceDescriptionModel,
    val count: Int
)