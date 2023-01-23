package orderdetails.domain.model

import orderdetails.domain.model.details.OrderDetailsAddressModel
import orderdetails.domain.model.details.OrderDetailsMarketplaceModel
import orderdetails.domain.model.details.OrderDetailsStorageModel
import orderdetails.domain.model.extras.OrderDetailsExtrasModel
import orderdetails.domain.model.status.OrderDetailsStatusProgress
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