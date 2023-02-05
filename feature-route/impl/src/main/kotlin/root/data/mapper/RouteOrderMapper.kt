package root.data.mapper

import orderdetails.root.data.model.details.OrderDetailsAddressDto
import orderdetails.root.data.model.OrderDetailsDto
import orderdetails.root.data.model.extras.OrderDetailsExtrasDto
import orderdetails.root.data.model.details.OrderDetailsMarketplaceDto
import orderdetails.root.data.model.extras.OrderDetailsPriceDescriptionDto
import orderdetails.root.data.model.details.OrderDetailsStorageDto
import orderdetails.root.domain.model.details.OrderDetailsAddressModel
import orderdetails.root.domain.model.details.OrderDetailsClientModel
import orderdetails.root.domain.model.extras.OrderDetailsExtrasModel
import orderdetails.root.domain.model.details.OrderDetailsMarketplaceModel
import orderdetails.root.domain.model.OrderDetailsModel
import orderdetails.root.domain.model.extras.OrderDetailsPriceDescriptionModel
import orderdetails.root.domain.model.status.OrderDetailsStatusProgress
import orderdetails.root.domain.model.details.OrderDetailsStorageModel
import org.threeten.bp.format.DateTimeFormatter
import root.data.model.RouteOrderDto
import root.domain.model.RouteOrderModel
import utils.ext.toLocalZonedDateTime

class RouteOrderMapper {
    fun map(dto: RouteOrderDto) = RouteOrderModel(
        details = mapOrderToDomain(dto.order),
        client = OrderDetailsClientModel(dto.client.name, dto.client.surname, dto.client.phone),
        index = dto.index
    )

    private fun mapOrderToDomain(dto: OrderDetailsDto) = OrderDetailsModel(
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
        status = OrderDetailsStatusProgress.values().first { it.status == dto.status },
        storage = mapStorageToDomain(dto.storage),
        weight = dto.weight
    )

    private fun mapAddressToDomain(dto: OrderDetailsAddressDto) = OrderDetailsAddressModel(
        id = dto.id,
        city = dto.city,
        comment = dto.comment,
        house = dto.house,
        street = dto.street
    )

    private fun mapExtrasToDomain(dto: OrderDetailsExtrasDto) = OrderDetailsExtrasModel(
        price = dto.price,
        id = dto.id,
        name = dto.name,
        priceDescription = mapPriceDescriptionToDomain(dto.priceDescription)
    )

    private fun mapMarketplaceToDomain(dto: OrderDetailsMarketplaceDto) =
        OrderDetailsMarketplaceModel(
            id = dto.id,
            name = WILDBERRIES
        )

    private fun mapStorageToDomain(dto: OrderDetailsStorageDto) = OrderDetailsStorageModel(
        id = dto.id,
        address = dto.address,
        name = dto.name
    )

    private fun mapPriceDescriptionToDomain(dto: OrderDetailsPriceDescriptionDto) =
        OrderDetailsPriceDescriptionModel(
            text = dto.text,
            isValid = dto.isValid
        )

    private companion object {
        const val WILDBERRIES = "Wildberries"
    }
}