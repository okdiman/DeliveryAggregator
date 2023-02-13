package orderdetails.root.domain.model.extras

data class OrderDetailsExtrasModel(
    val id: Long,
    val name: String,
    val price: Int,
    val priceDescription: OrderDetailsPriceDescriptionModel
)