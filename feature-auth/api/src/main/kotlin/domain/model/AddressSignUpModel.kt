package domain.model

import kotlinx.serialization.Serializable

@Serializable
class AddressSignUpModel(
    val geoLat: String,
    val geoLon: String,
    val city: String,
    val street: String,
    val house: String
)