package root.data.mapper

import root.data.model.AuthSignUpRequest
import domain.model.AuthSignUpModel

class AuthSignUpMapper {
    fun map(model: AuthSignUpModel): AuthSignUpRequest {
        return AuthSignUpRequest(
            code = model.code,
            phone = model.phone,
            email = model.email,
            name = model.name,
            surname = model.surname,
            secondName = model.secondName,
            inn = model.inn,
            kpp = model.kpp,
            ogrn = model.ogrn,
            bik = model.bik,
            legalAddress = model.legalAddress,
            actualAddress = model.actualAddress,
            checkingAccount = model.checkingAccount,
            correspondentAccount = model.correspondentAccount,
            address = model.address,
            organisationName = model.organisationName,
            bank = model.bank,
            carLoadCapacity = model.carLoadCapacity,
            carCategory = model.carCategory,
            carModel = model.carModel,
            carPalletCapacity = model.carPalletCapacity,
            carPlate = model.carPlate
        )
    }
}