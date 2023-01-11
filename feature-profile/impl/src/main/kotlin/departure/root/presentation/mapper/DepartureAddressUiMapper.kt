package departure.root.presentation.mapper

import departure.root.presentation.compose.model.DepartureAddressUiModel
import domain.model.AddressModel
import presentation.model.AddressUiModel
import utils.CommonConstants.Helpers.COMMA

class DepartureAddressUiMapper {
    fun mapToUi(models: List<AddressModel>) = models.map {
        val address = buildString {
            append(it.city + COMMA + it.street + COMMA + it.house)
        }
        DepartureAddressUiModel(
            address = address,
            isActive = it.isActive,
            id = it.id
        )
    }

    fun mapToDomain(model: AddressUiModel, id: String) = AddressModel(
        city = model.city,
        house = model.house,
        street = model.street,
        isActive = true,
        id = id,
        geoLon = model.geoLon,
        geoLat = model.geoLat
    )
}