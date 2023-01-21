package orderdetails.domain.model

data class OrderDetailsExtrasModel(
    val id: Int,
    val name: String,
    val price: Int,
    val priceDescription: OrderDetailsPriceDescriptionModel
)