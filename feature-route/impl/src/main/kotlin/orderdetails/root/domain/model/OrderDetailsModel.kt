package orderdetails.root.domain.model

import org.threeten.bp.LocalDateTime
import root.domain.model.OrderAddressModel
import root.domain.model.details.OrderMarketplaceModel
import root.domain.model.extras.OrderExtrasModel
import root.domain.model.status.OrderStatusProgress
import root.presentation.compose.model.RouteStorageModel
import java.math.BigDecimal

data class OrderDetailsModel(
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
    val price: BigDecimal,
    val status: OrderStatusProgress,
    val isPaid: Boolean,
    val storage: RouteStorageModel,
    val weight: Int,
    val load: OrderLoadModel?,
    val delivery: OrderDeliveryModel?,
)
