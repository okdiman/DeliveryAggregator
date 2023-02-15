package root.data.mapper

import domain.model.AuthSignUpModel
import root.data.model.AuthSignUpRequest

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
            organisationName = model.organisationName,
            bank = model.bank
        )
    }
}