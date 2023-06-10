package root.data.mapper

import androidx.core.net.toUri
import orderdetails.root.data.model.OrderDetailsDto
import orderdetails.root.domain.model.OrderDeliveryModel
import orderdetails.root.domain.model.OrderDetailsModel
import orderdetails.root.domain.model.OrderLoadModel
import orderdetails.root.domain.model.details.OrderDetailsClientModel
import orderdetails.root.domain.model.details.OrderDetailsContractorModel
import org.threeten.bp.format.DateTimeFormatter
import root.data.model.response.ContractorDto
import root.data.model.response.OrderAddressDto
import root.data.model.response.OrderDto
import root.data.model.response.OrderExtrasDetailsDto
import root.data.model.response.OrderMarketplaceDto
import root.data.model.response.OrderPriceDescriptionDto
import root.data.model.response.OrderStorageDto
import root.data.model.response.RouteOrderDto
import root.domain.model.OrderAddressModel
import root.domain.model.OrderModel
import root.domain.model.RouteOrderModel
import root.domain.model.details.OrderMarketplaceModel
import root.domain.model.extras.OrderExtrasModel
import root.domain.model.extras.OrderPriceDescriptionModel
import root.domain.model.status.OrderStatusProgress
import root.presentation.compose.model.RouteStorageModel
import utils.ext.toLocalZonedDateTime

class RouteOrderMapper {

    fun map(dto: RouteOrderDto) = RouteOrderModel(
        details = mapOrderToDomain(dto.order),
        client = OrderDetailsClientModel(dto.client.name, dto.client.surname, dto.client.phone),
    )

    fun map(dto: OrderDto) = OrderModel(
        details = mapOrderToDomain(dto.order),
        contractor = dto.contractor?.let(::mapContractorToDomain)
    )

    fun mapOrderToDomain(dto: OrderDetailsDto) = OrderDetailsModel(
        id = dto.id,
        boxes = dto.boxes,
        address = mapAddressToDomain(dto.address),
        organizationName = dto.organizationName,
        arrivalDay = dto.arrivalDay.toLocalZonedDateTime(DateTimeFormatter.ISO_DATE_TIME),
        arrivalTime = dto.arrivalTime,
        comment = dto.comment,
        price = dto.price,
        extras = dto.extras?.map { extrasDto ->
            mapExtrasToDomain(extrasDto.extra, extrasDto.quantity)
        },
        marketplace = mapMarketplaceToDomain(dto.marketplace),
        pallets = dto.pallets,
        status = OrderStatusProgress.values().first { it.status == dto.status },
        storage = mapStorageToDomain(dto.storage),
        weight = dto.weight,
        isPaid = dto.isPaid,
        load = mapLoadToDomain(dto.loadTime, dto.loadImages),
        delivery = mapDeliveryToDomain(dto.deliveryTime, dto.deliveryImages),
    )

    private fun mapAddressToDomain(dto: OrderAddressDto) = OrderAddressModel(
        id = dto.id,
        city = dto.city,
        comment = dto.comment,
        house = dto.house,
        street = dto.street
    )

    internal fun mapExtrasToDomain(dto: OrderExtrasDetailsDto, count: Int = 0) = OrderExtrasModel(
        price = dto.price,
        id = dto.id,
        name = dto.name,
        priceDescription = mapPriceDescriptionToDomain(dto.priceDescription),
        count = count
    )

    private fun mapContractorToDomain(dto: ContractorDto) = OrderDetailsContractorModel(
        phone = dto.phone,
        email = dto.email,
        surname = dto.surname,
        name = dto.name,
        secondName = dto.secondName,
        carPlate = dto.carPlate,
        carModel = dto.carModel,
    )

    private fun mapLoadToDomain(time: String?, images: List<String>?) = time?.let { t ->
        OrderLoadModel(
            loadDateTime = t.toLocalZonedDateTime(DateTimeFormatter.ISO_DATE_TIME),
            images = images?.map(String::toUri).orEmpty()
        )
    }

    private fun mapDeliveryToDomain(time: String?, images: List<String>?) = time?.let { t ->
        OrderDeliveryModel(
            deliveryDateTime = t.toLocalZonedDateTime(DateTimeFormatter.ISO_DATE_TIME),
            images = images?.map(String::toUri).orEmpty()
        )
    }

    private fun mapMarketplaceToDomain(dto: OrderMarketplaceDto) =
        OrderMarketplaceModel(
            id = dto.id,
            name = WILDBERRIES
        )

    internal fun mapStorageToDomain(dto: OrderStorageDto) = RouteStorageModel(
        id = dto.id,
        address = dto.address,
        name = dto.name,
        weekWorkDays = dto.weekWorkDays,
        dayOffs = dto.dayOffs.values.map { it.toString().replace("\"", "") }
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