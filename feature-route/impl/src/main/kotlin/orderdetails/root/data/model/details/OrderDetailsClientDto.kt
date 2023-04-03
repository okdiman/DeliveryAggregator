package orderdetails.root.data.model.details

import kotlinx.serialization.Serializable

@Serializable
data class OrderDetailsClientDto(
    val name: String,
    val surname: String,
    val phone: String?
)
