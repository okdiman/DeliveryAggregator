package data.mapper

import data.model.request.ProfileRequest
import data.model.response.ProfileDto
import domain.ProfileModel

class ProfileModelMapper {
    fun mapFromDataToDomain(dto: ProfileDto) = ProfileModel(
        email = dto.contractor.email,
        name = dto.contractor.name,
        surname = dto.contractor.surname,
        phone = dto.user.phone,
        secondName = dto.contractor.secondName,
        ogrn = dto.contractor.ogrn,
        bik = dto.contractor.bik,
        inn = dto.contractor.inn,
        kpp = dto.contractor.kpp,
        bank = dto.contractor.bank,
        organizationName = dto.contractor.organisationName,
        actualAddress = dto.contractor.actualAddress,
        legalAddress = dto.contractor.legalAddress,
        checkingAccount = dto.contractor.checkingAccount,
        correspondentAccount = dto.contractor.correspondentAccount,
        licencePlate = dto.contractor.carPlate,
        carModel = dto.contractor.carModel,
        carCategory = dto.contractor.carCategory,
        carLoadCapacity = dto.contractor.carLoadCapacity,
        carCapacity = dto.contractor.carCapacity
    )

    fun mapFromDomainToData(model: ProfileModel) = ProfileRequest(
        email = model.email,
        name = model.name,
        surname = model.surname,
        secondName = model.secondName,
        ogrn = model.ogrn,
        bik = model.bik,
        inn = model.inn,
        kpp = model.kpp,
        bank = model.bank,
        organisationName = model.organizationName,
        actualAddress = model.actualAddress,
        legalAddress = model.legalAddress,
        checkingAccount = model.checkingAccount,
        correspondentAccount = model.correspondentAccount,
        carPlate = model.licencePlate,
        carModel = model.carModel,
        carCategory = model.carCategory,
        carLoadCapacity = model.carLoadCapacity,
        carCapacity = model.carCapacity
    )
}