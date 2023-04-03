package root.data.mapper

import domain.model.AddressModel
import root.data.model.AddressDto
import root.data.model.AddressRequest

class AddressMapper {
    fun mapToDomain(dto: List<AddressDto>) = dto.map {
        AddressModel(
            city = it.city,
            geoLat = it.geoLat,
            geoLon = it.geoLon,
            house = it.house,
            street = it.street,
            id = it.id,
            isActive = it.isActive,
            comment = it.comment
        )
    }

    fun mapToData(model: AddressModel) = AddressRequest(
        city = model.city,
        geoLat = model.geoLat,
        geoLon = model.geoLon,
        house = model.house,
        street = model.street,
        isActive = model.isActive,
        comment = model.comment
    )
}
