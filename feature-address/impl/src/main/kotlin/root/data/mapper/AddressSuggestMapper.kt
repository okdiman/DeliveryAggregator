package root.data.mapper

import domain.model.AddressSuggestDetailedModel
import domain.model.AddressSuggestModel
import root.data.model.response.AddressSuggestDto

class AddressSuggestMapper {
    fun map(dto: List<AddressSuggestDto>): List<AddressSuggestModel> {
        return dto.map { suggest ->
            AddressSuggestModel(
                value = suggest.value,
                data = AddressSuggestDetailedModel(
                    geoLat = suggest.data.geoLat,
                    geoLon = suggest.data.geoLon,
                    city = suggest.data.city,
                    street = suggest.data.street,
                    house = suggest.data.house,
                    district = suggest.data.district,
                    block = suggest.data.block,
                    blockType = suggest.data.blockType,
                    settlement = suggest.data.settlement,
                    area = suggest.data.area
                )
            )
        }
    }
}