package orderdetails.domain.model.details

data class OrderDetailsAddressModel(
    val city: String,
    val comment: String,
    val house: String,
    val id: Int,
    val street: String
)