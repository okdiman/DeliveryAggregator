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
        ogrn = dto.info.ogrn,
        bik = dto.info.bik,
        inn = dto.info.inn,
        kpp = dto.info.kpp,
        bank = dto.info.bank,
        organizationName = dto.info.organisationName,
        actualAddress = dto.info.actualAddress,
        legalAddress = dto.info.legalAddress,
        checkingAccount = dto.info.checkingAccount,
        correspondentAccount = dto.info.correspondentAccount,
        licencePlate = dto.info.carPlate,
        carModel = dto.info.carModel,
        carCategory = dto.info.carCategory,
        carLoadCapacity = dto.info.carLoadCapacity,
        carCapacity = dto.info.carCapacity
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
        carPalletCapacity = model.carCapacity
    )
}