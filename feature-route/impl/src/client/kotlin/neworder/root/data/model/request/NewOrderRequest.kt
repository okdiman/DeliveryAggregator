package neworder.root.data.model.request

data class NewOrderRequest(
    val addressId: Int,
    val arrivalDay: String,
    val arrivalTime: String,
    val boxes: Int,
    val comment: String,
    val cargoType: String,
    val marketplaceId: Int,
    val pallets: Int,
    val storageId: Int,
    val weight: Int,
    val extrasIds: List<Long>
)