package root.domain.model

import root.domain.OrderStatusProgress

data class OrderModel(
    val address: OrderAddressModel,
    val arrivalDay: String,
    val arrivalTime: String,
    val boxes: Int,
    val comment: String,
    val extras: List<ExtrasModel>?,
    val id: Long,
    val marketplace: MarketplaceModel,
    val pallets: Int,
    val price: Int,
    val status: OrderStatusProgress?,
    val storage: StorageModel,
    val weight: Int
)