package root.data.model

import root.domain.OrderStatusProgress

data class OrderDto(
    val address: OrderAddressDto,
    val arrivalDay: String,
    val arrivalTime: String,
    val boxes: Int,
    val comment: String,
    val extras: List<ExtrasDto>?,
    val id: Long,
    val marketplace: MarketplaceDto,
    val pallets: Int,
    val price: Int,
    val status: OrderStatusProgress?,
    val storage: StorageDto,
    val weight: Int
)