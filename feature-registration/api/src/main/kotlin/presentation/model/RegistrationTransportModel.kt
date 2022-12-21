package presentation.model

import androidx.compose.runtime.Immutable

@Immutable
class RegistrationTransportModel(
    val licencePlate: String,
    val departureAddress: String,
    val carBrand: String,
    val carCategory: String,
    val carLoadCapacity: String,
    val carCapacity: String
)