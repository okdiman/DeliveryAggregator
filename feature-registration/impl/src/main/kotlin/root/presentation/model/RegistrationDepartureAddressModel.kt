package root.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
class RegistrationDepartureAddressModel(
    val geoLat: String,
    val geoLon: String,
    val city: String,
    val street: String,
    val house: String
)