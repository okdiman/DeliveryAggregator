package orderdetails.loadingstate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoadingStateRequest(
    val images: List<String>,
    val boxes: Int,
    val pallets: Int,
    val extras: List<Long>,
    val cargoType: String
)