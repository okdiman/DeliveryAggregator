package user.presentation.mapper

import domain.model.AuthAddressSignUpModel
import domain.model.AuthSignUpModel
import user.presentation.UserParameters
import user.presentation.viewmodel.model.UserState

class SignUpModelMapper {
    fun map(parameters: UserParameters, state: UserState): AuthSignUpModel {
        return AuthSignUpModel(
            code = parameters.user.code.toInt(),
            phone = parameters.user.phone,
            email = state.email.stateText,
            name = state.name.stateText,
            surname = state.surname.stateText,
            secondName = state.secondName.stateText,
            inn = parameters.company.inn,
            kpp = parameters.company.kpp,
            ogrn = parameters.company.ogrn,
            bik = parameters.bank.bik,
            legalAddress = parameters.company.legalAddress.orEmpty(),
            actualAddress = parameters.company.actualAddress.orEmpty(),
            checkingAccount = parameters.bank.paymentAcc,
            correspondentAccount = parameters.bank.corrAcc,
            organisationName = parameters.company.companyName,
            bank = parameters.bank.bankName,
            address = AuthAddressSignUpModel(
                geoLon = parameters.company.fullAddress.geoLon,
                geoLat = parameters.company.fullAddress.geoLat,
                city = parameters.company.fullAddress.city,
                street = parameters.company.fullAddress.street,
                house = parameters.company.fullAddress.house
            )
        )
    }
}