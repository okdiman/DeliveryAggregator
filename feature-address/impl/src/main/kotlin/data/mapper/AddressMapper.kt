package data.mapper

import data.model.request.AddressRequest
import data.model.response.AddressDto
import domain.model.AddressModel

class AddressMapper {
    fun mapToDomain(dto: List<AddressDto>) = dto.map {
        AddressModel(
            city = it.city,
            geoLat = it.geoLat,
            geoLon = it.geoLon,
            house = it.house,
            street = it.street,
            id = it.id,
            isActive = it.isActive
        )
    }

    fun mapToData(model: AddressModel) = AddressRequest(
        city = model.city,
        geoLat = model.geoLat,
        geoLon = model.geoLon,
        house = model.house,
        street = model.street,
        isActive = model.isActive
    )
}