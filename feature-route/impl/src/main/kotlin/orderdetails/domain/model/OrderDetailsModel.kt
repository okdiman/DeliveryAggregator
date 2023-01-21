package orderdetails.domain.model

data class OrderDetailsModel(
    val address: OrderDetailsAddressModel,
    val arrivalDay: String,
    val arrivalTime: String,
    val boxes: Int,
    val comment: String,
    val extras: List<OrderDetailsExtrasModel>?,
    val id: Long,
    val marketplace: OrderDetailsMarketplaceModel,
    val pallets: Int,
    val price: Int,
    val status: OrderDetailsStatusProgress,
    val storage: OrderDetailsStorageModel,
    val weight: Int
)