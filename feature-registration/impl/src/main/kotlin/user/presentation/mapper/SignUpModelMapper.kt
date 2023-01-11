package user.presentation.mapper

import domain.model.AddressSignUpModel
import domain.model.SignUpModel
import presentation.parameters.UserParameters
import user.presentation.viewmodel.model.UserState

class SignUpModelMapper {
    fun map(parameters: UserParameters, state: UserState): SignUpModel {
        return SignUpModel(
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
            address = AddressSignUpModel(
                geoLon = parameters.transport.departureAddress.geoLon,
                geoLat = parameters.transport.departureAddress.geoLat,
                city = parameters.transport.departureAddress.city,
                street = parameters.transport.departureAddress.street,
                house = parameters.transport.departureAddress.house
            ),
            organisationName = parameters.company.companyName,
            bank = parameters.bank.bankName,
            carLoadCapacity = parameters.transport.carLoadCapacity.toDouble(),
            carCategory = parameters.transport.carCategory,
            carModel = parameters.transport.carBrand,
            carPalletCapacity = parameters.transport.carCapacity.toInt(),
            carPlate = parameters.transport.licencePlate
        )
    }
}