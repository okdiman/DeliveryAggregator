package user.presentation.mapper

import domain.model.SignUpModel
import presentation.parameters.UserParameters
import user.presentation.viewmodel.model.UserState

class SignUpModelMapper {
    fun map(parameters: UserParameters, state: UserState): SignUpModel {
        return SignUpModel(
            code = parameters.user.code.toInt(),
            phone = parameters.user.phone,
            email = state.email.text,
            name = state.name.text,
            surname = state.surname.text,
            secondName = state.secondName.text,
            inn = parameters.company.inn,
            kpp = parameters.company.kpp,
            ogrn = parameters.company.ogrn,
            bik = parameters.bank.bik,
            legalAddress = parameters.company.legalAddress,
            actualAddress = parameters.company.actualAddress,
            checkingAccount = parameters.bank.paymentAcc,
            correspondentAccount = parameters.bank.corrAcc,
            address = parameters.transport.departureAddress,
            organisationName = parameters.company.companyName,
            bank = parameters.bank.bankName,
            carLoadCapacity = parameters.transport.carLoadCapacity.toInt(),
            carCategory = parameters.transport.carCategory,
            carModel = parameters.transport.carBrand,
            carPalletCapacity = parameters.transport.carCapacity.toInt(),
            carPlate = parameters.transport.licencePlate
        )
    }
}