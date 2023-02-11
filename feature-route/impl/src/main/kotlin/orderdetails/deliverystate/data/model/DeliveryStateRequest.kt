package orderdetails.deliverystate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DeliveryStateRequest(
    val images: ArrayList<String>,
    val comment: String,
    val isProblem: Boolean
)