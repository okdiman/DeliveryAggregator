package orderdetails.root.data.model

import com.google.gson.annotations.SerializedName
import root.data.model.response.OrderAddressDto
import root.data.model.response.OrderExtrasDto
import root.data.model.response.OrderMarketplaceDto
import root.data.model.response.OrderStorageDto

data class OrderDetailsDto(
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