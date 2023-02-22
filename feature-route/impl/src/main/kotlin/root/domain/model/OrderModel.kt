package root.domain.model

import org.threeten.bp.LocalDateTime
import root.domain.model.details.OrderMarketplaceModel
import root.domain.model.details.OrderStorageModel
import root.domain.model.extras.OrderExtrasModel
import root.domain.model.status.OrderStatusProgress

data class OrderModel(
    val address: OrderAddressModel,
    val arrivalDay: LocalDateTime,
    val arrivalTime: String,
    val boxes: Int,
    val comment: String,
    val organizationName: String,
    val extras: List<OrderExtrasModel>?,
    val id: Long,
    val marketplace: OrderMarketplaceModel,
    val pallets: Int,
    val price: Int,
    val status: OrderStatusProgress,
    val storage: OrderStorageModel,
    val weight: Int
)
