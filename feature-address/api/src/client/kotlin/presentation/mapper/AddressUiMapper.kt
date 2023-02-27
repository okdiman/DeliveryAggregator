package presentation.mapper

import domain.model.AddressModel
import presentation.AddressSuggestUiModel
import presentation.model.AddressUiModel
import utils.CommonConstants.Helpers.COMMA

class AddressUiMapper {
    fun mapToUi(models: List<AddressModel>) = models.map {
        val address = buildString {
            append(it.city + COMMA + it.street + COMMA + it.house)
        }
        AddressUiModel(
            address = address,
            isActive = it.isActive,
            id = it.id,
            comment = it.comment
        )
    }

    fun mapToDomain(model: AddressSuggestUiModel, id: String) = AddressModel(
        city = model.city,
        house = model.house,
        street = model.street,
        isActive = true,
        id = id,
        geoLon = model.geoLon,
        geoLat = model.geoLat,
        comment = model.comment
    )
}