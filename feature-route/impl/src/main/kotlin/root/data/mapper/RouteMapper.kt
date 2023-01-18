package root.data.mapper

import root.data.model.ExtrasDto
import root.data.model.MarketplaceDto
import root.data.model.OrderAddressDto
import root.data.model.PriceDescriptionDto
import root.data.model.RouteDto
import root.data.model.StorageDto
import root.domain.model.ExtrasModel
import root.domain.model.MarketplaceModel
import root.domain.model.OrderAddressModel
import root.domain.model.OrderModel
import root.domain.model.PriceDescriptionModel
import root.domain.model.RouteModel
import root.domain.model.StorageModel

class RouteMapper {
    fun map(dto: RouteDto): RouteModel {
        return RouteModel(orders = dto.orders.map {
            OrderModel(
                id = it.id,
                boxes = it.boxes,
                address = mapAddressToDomain(it.address),
                arrivalDay = it.arrivalDay,
                arrivalTime = it.arrivalTime,
                comment = it.comment,
                price = it.price,
                extras = it.extras?.map { extrasDto ->
                    mapExtrasToDomain(extrasDto)
                },
                marketplace = mapMarketplaceToDomain(it.marketplace),
                pallets = it.pallets,
                status = it.status,
                storage = mapStorageToDomain(it.storage),
                weight = it.weight
            )
        })
    }

    private fun mapAddressToDomain(dto: OrderAddressDto) = OrderAddressModel(
        id = dto.id,
        city = dto.city,
        comment = dto.comment,
        house = dto.house,
        street = dto.street
    )

    private fun mapExtrasToDomain(dto: ExtrasDto) = ExtrasModel(
        price = dto.price,
        id = dto.id,
        name = dto.name,
        priceDescription = mapPriceDescriptionToDomain(dto.priceDescription)
    )

    private fun mapMarketplaceToDomain(dto: MarketplaceDto) = MarketplaceModel(
        id = dto.id,
        name = WILDBERRIES
    )

    private fun mapStorageToDomain(dto: StorageDto) = StorageModel(
        id = dto.id,
        address = dto.address,
        name = dto.name
    )

    private fun mapPriceDescriptionToDomain(dto: PriceDescriptionDto) = PriceDescriptionModel(
        text = dto.text,
        isValid = dto.isValid
    )

    private companion object {
        const val WILDBERRIES = "Wildberries"
    }
}