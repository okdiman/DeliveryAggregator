package presentation.parameters.model

import androidx.compose.runtime.Immutable

@Immutable
class RegistrationTransportModel(
    val licencePlate: String,
    val departureAddress: DepartureAddressModel,
    val carBrand: String,
    val carCategory: String,
    val carLoadCapacity: String,
    val carCapacity: String
)