package orderdetails.root.domain.model

import orderdetails.root.domain.model.details.OrderDetailsAddressModel
import orderdetails.root.domain.model.details.OrderDetailsMarketplaceModel
import orderdetails.root.domain.model.details.OrderDetailsStorageModel
import orderdetails.root.domain.model.extras.OrderDetailsExtrasModel
import orderdetails.root.domain.model.status.OrderDetailsStatusProgress
import org.threeten.bp.LocalDateTime

data class OrderDetailsModel(
    val address: OrderDetailsAddressModel,
    val arrivalDay: LocalDateTime,
    val arrivalTime: String,
    val boxes: Int,
    val comment: String,
    val organizationName: String,
    val extras: List<OrderDetailsExtrasModel>?,
    val id: Long,
    val marketplace: OrderDetailsMarketplaceModel,
    val pallets: Int,
    val price: Int,
    val status: OrderDetailsStatusProgress,
    val storage: OrderDetailsStorageModel,
    val weight: Int
)