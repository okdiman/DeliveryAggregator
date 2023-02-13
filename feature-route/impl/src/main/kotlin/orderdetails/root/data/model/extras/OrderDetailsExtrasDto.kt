package orderdetails.root.data.model.extras

class OrderDetailsExtrasDto(
    val id: Long,
    val name: String,
    val price: Int,
    val priceDescription: OrderDetailsPriceDescriptionDto
)