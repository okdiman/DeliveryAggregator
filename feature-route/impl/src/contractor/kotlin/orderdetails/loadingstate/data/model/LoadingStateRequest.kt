package orderdetails.loadingstate.data.model

import extras.data.ExtrasDto
import kotlinx.serialization.Serializable

@Serializable
data class LoadingStateRequest(
    val images: List<String>,
    val boxes: Int,
    val pallets: Int,
    val extras: List<ExtrasDto>,
    val cargoType: String
)