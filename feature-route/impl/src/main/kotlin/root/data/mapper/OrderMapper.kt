package root.data.mapper

import org.threeten.bp.format.DateTimeFormatter
import root.data.model.response.OrderAddressDto
import root.data.model.response.OrderDto
import root.data.model.response.OrderExtrasDto
import root.data.model.response.OrderMarketplaceDto
import root.data.model.response.OrderPriceDescriptionDto
import root.data.model.response.OrderStorageDto
import root.domain.model.OrderAddressModel
import root.domain.model.OrderModel
import root.domain.model.details.OrderMarketplaceModel
import root.domain.model.details.OrderStorageModel
import root.domain.model.extras.OrderExtrasModel
import root.domain.model.extras.OrderPriceDescriptionModel
import root.domain.model.status.OrderStatusProgress
import utils.ext.toLocalZonedDateTime

class OrderMapper {

    fun mapOrderToDomain(dto: OrderDto) = OrderModel(
        id = dto.id,
        boxes = dto.boxes,
        address = mapAddressToDomain(dto.address),
        organizationName = dto.organizationName,
        arrivalDay = dto.arrivalDay.toLocalZonedDateTime(DateTimeFormatter.ISO_DATE_TIME),
        arrivalTime = dto.arrivalTime,
        comment = dto.comment,
        price = dto.price,
        extras = dto.extras?.map { extrasDto ->
            mapExtrasToDomain(extrasDto)
        },
        marketplace = mapMarketplaceToDomain(dto.marketplace),
        pallets = dto.pallets,
        status = OrderStatusProgress.values().first { it.status == dto.status },
        storage = mapStorageToDomain(dto.storage),
        weight = dto.weight
    )

    private fun mapAddressToDomain(dto: OrderAddressDto) = OrderAddressModel(
        id = dto.id,
        city = dto.city,
        comment = dto.comment,
        house = dto.house,
        street = dto.street
    )

    internal fun mapExtrasToDomain(dto: OrderExtrasDto) = OrderExtrasModel(
        price = dto.price,
        id = dto.id,
        name = dto.name,
        priceDescription = mapPriceDescriptionToDomain(dto.priceDescription)
    )

    private fun mapMarketplaceToDomain(dto: OrderMarketplaceDto) =
        OrderMarketplaceModel(
            id = dto.id,
            name = WILDBERRIES
        )

    private fun mapStorageToDomain(dto: OrderStorageDto) = OrderStorageModel(
        id = dto.id,
        address = dto.address,
        name = dto.name
    )

    private fun mapPriceDescriptionToDomain(dto: OrderPriceDescriptionDto) =
        OrderPriceDescriptionModel(
            text = dto.text,
            isValid = dto.isValid
        )

    private companion object {
        const val WILDBERRIES = "Wildberries"
    }
}
