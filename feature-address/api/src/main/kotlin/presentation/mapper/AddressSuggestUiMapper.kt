package presentation.mapper

import data.AddressConstants.MAX_SUGGESTS
import domain.model.AddressSuggestModel
import presentation.AddressUiModel

class AddressSuggestUiMapper {
    fun map(items: List<AddressSuggestModel>): List<AddressUiModel> {
        return items.map { model ->
            val street = model.data.street.ifEmpty { model.data.district }
            val subtitle = buildString {
                append(street)
                if (model.data.house.isNotEmpty()) {
                    append(", ")
                    append(model.data.house)
                }
                if (model.data.block.isNotEmpty()) {
                    append(", ")
                    append(model.data.blockType)
                    append(" ")
                    append(model.data.block)
                }
            }
            val city = buildString {
                append(model.data.city)
                if (model.data.area.isNotEmpty()) {
                    append(", ")
                    append(model.data.area)
                }
                if (model.data.settlement.isNotEmpty()) {
                    append(", ")
                    append(model.data.settlement)
                }
            }
            AddressUiModel(
                value = model.value,
                street = street,
                house = model.data.house,
                city = city,
                geo_lon = model.data.geo_lon,
                geo_lat = model.data.geo_lat,
                subtitle = subtitle
            )
        }.take(MAX_SUGGESTS)
    }
}