package neworder.root.data.model.request

import extras.data.ExtrasDto
import kotlinx.serialization.Serializable

@Serializable
data class NewOrderRequest(
    val addressId: Long,
    val arrivalDay: String,
    val arrivalTime: String,
    val boxes: Int,
    val comment: String,
    val cargoType: String,
    val marketplaceId: Int,
    val pallets: Int,
    val storageId: Int,
    val weight: Int,
    val extras: List<ExtrasDto>
)