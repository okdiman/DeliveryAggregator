package orderdetails.loadingstate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoadingStateRequest(
    val images: ArrayList<String>,
    val boxes: Int,
    val pallets: Int,
    val extras: ArrayList<Int>,
    val cargoType: String
)