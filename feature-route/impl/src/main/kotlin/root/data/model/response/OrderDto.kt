package root.data.model.response

import com.google.gson.annotations.SerializedName

data class OrderDto(
    val address: OrderAddressDto,
    val arrivalDay: String,
    val arrivalTime: String,
    val boxes: Int,
    val comment: String,
    val extras: List<OrderExtrasDto>?,
    @SerializedName("organisationName")
    val organizationName: String,
    val id: Long,
    val marketplace: OrderMarketplaceDto,
    val pallets: Int,
    val price: Int,
    val status: String?,
    val storage: OrderStorageDto,
    val weight: Int
)
