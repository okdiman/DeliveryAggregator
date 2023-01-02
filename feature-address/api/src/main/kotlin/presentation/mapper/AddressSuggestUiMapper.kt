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
                    append(COMMA + model.data.house)
                }
                if (model.data.block.isNotEmpty()) {
                    append(COMMA + model.data.blockType + model.data.block)
                }
            }
            val city = buildString {
                append(model.data.city)
                if (model.data.area.isNotEmpty()) {
                    append(COMMA + model.data.area)
                }
                if (model.data.settlement.isNotEmpty()) {
                    append(COMMA + model.data.settlement)
                }
            }
            AddressUiModel(
                value = model.value,
                street = street,
                house = model.data.house,
                city = city,
                geoLon = model.data.geoLon,
                geoLat = model.data.geoLat,
                subtitle = subtitle
            )
        }.take(MAX_SUGGESTS)
    }

    companion object {
        private const val COMMA = ", "
    }
}