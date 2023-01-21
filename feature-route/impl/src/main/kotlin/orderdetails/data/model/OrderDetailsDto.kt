package orderdetails.data.model

import com.google.gson.annotations.SerializedName

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