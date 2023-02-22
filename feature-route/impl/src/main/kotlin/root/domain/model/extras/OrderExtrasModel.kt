package root.domain.model.extras

data class OrderExtrasModel(
    val id: Long,
    val name: String,
    val price: Int,
    val priceDescription: OrderPriceDescriptionModel
)
