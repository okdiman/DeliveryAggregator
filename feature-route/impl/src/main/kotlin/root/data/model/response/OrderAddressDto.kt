package root.data.model.response

import kotlinx.serialization.Serializable

@Serializable
class OrderAddressDto(
    val city: String,
    val comment: String,
    val house: String,
    val id: Int,
    val street: String
)
