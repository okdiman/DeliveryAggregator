package root.data.mapper

import domain.model.ProfileModel
import root.data.model.request.ProfileRequest
import root.data.model.response.ProfileDto

class ProfileModelMapper {
    fun mapFromDataToDomain(dto: ProfileDto) = ProfileModel(
        email = dto.info.email,
        name = dto.info.name,
        surname = dto.info.surname,
        phone = dto.user.phone,
        secondName = dto.info.secondName,
        organizationName = dto.info.organisationName
    )

    fun mapFromDomainToData(model: ProfileModel) = ProfileRequest(
        email = model.email,
        name = model.name,
        surname = model.surname,
        secondName = model.secondName,
        organisationName = model.organizationName
    )
}