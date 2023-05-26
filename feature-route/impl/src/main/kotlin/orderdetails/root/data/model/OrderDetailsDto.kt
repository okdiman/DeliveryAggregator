package orderdetails.root.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import root.data.model.response.OrderAddressDto
import root.data.model.response.OrderExtrasDto
import root.data.model.response.OrderMarketplaceDto
import root.data.model.response.OrderStorageDto
import utils.serializers.BigDecimalAsDoubleSerializer
import java.math.BigDecimal

/**
 * В приложении используются другие наименования полей: "load" - погрузка,
 * "delivery" - доставка, тогда как на сервере "delivery" - погрузка, "done" - доставка,
 * поэтому для этих полей используются `@SerialName`
 */
@Serializable
data class OrderDetailsDto(
    val address: OrderAddressDto,
    val arrivalDay: String,
    val arrivalTime: String,
    val boxes: Int,
    val comment: String,
    val extras: List<OrderExtrasDto>?,
    @SerialName("organisationName")
    val organizationName: String,
    val id: Long,
    val marketplace: OrderMarketplaceDto,
    val pallets: Int,
    @Serializable(with = BigDecimalAsDoubleSerializer::class)
    val price: BigDecimal,
    @SerialName("deliveryImages")
    val loadImages: List<String>?,
    @SerialName("doneImages")
    val deliveryImages: List<String>?,
    @SerialName("deliveryTime")
    val loadTime: String?,
    @SerialName("doneTime")
    val deliveryTime: String?,
    val status: String?,
    val cargoType: String,
    val storage: OrderStorageDto,
    val weight: Int,
    val isPaid: Boolean
)
