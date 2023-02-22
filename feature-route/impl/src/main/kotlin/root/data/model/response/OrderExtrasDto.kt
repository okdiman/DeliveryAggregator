package root.data.model.response

class OrderExtrasDto(
    val id: Long,
    val name: String,
    val price: Int,
    val priceDescription: OrderPriceDescriptionDto
)
