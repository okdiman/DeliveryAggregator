package data.mapper

import data.model.response.AddressSuggestDto
import domain.model.AddressSuggestDetailedModel
import domain.model.AddressSuggestModel

class AddressSuggestMapper {
    fun map(dto: List<AddressSuggestDto>): List<AddressSuggestModel> {
        return dto.map { suggest ->
            AddressSuggestModel(
                value = suggest.value,
                data = AddressSuggestDetailedModel(
                    geo_lat = suggest.data.geo_lat,
                    geo_lon = suggest.data.geo_lon,
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