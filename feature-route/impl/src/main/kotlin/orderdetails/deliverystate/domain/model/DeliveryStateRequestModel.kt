package orderdetails.deliverystate.domain.model

class DeliveryStateRequestModel(
    val images: ArrayList<String>,
    val comment: String,
    val isProblem: Boolean
)