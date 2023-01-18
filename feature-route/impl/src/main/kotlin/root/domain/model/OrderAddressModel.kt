package root.domain.model

data class OrderAddressModel(
    val city: String,
    val comment: String,
    val house: String,
    val id: Int,
    val street: String
)