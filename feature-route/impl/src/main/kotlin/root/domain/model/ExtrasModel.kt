package root.domain.model

data class ExtrasModel(
    val id: Int,
    val name: String,
    val price: Int,
    val priceDescription: PriceDescriptionModel
)