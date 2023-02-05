package orderdetails.root.data.model

import com.google.gson.annotations.SerializedName
import orderdetails.root.data.model.details.OrderDetailsAddressDto
import orderdetails.root.data.model.details.OrderDetailsMarketplaceDto
import orderdetails.root.data.model.details.OrderDetailsStorageDto
import orderdetails.root.data.model.extras.OrderDetailsExtrasDto

data class OrderDetailsDto(
    val address: OrderDetailsAddressDto,
    val arrivalDay: String,
    val arrivalTime: String,
    val boxes: Int,
    val comment: String,
    val extras: List<OrderDetailsExtrasDto>?,
    @SerializedName("organisationName")
    val organizationName: String,
    val id: Long,
    val marketplace: OrderDetailsMarketplaceDto,
    val pallets: Int,
    val price: Int,
    val status: String?,
    val storage: OrderDetailsStorageDto,
    val weight: Int
)