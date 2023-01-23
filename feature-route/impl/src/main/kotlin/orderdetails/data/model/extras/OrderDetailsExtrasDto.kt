package orderdetails.data.model.extras

class OrderDetailsExtrasDto(
    val id: Int,
    val name: String,
    val price: Int,
    val priceDescription: OrderDetailsPriceDescriptionDto
)