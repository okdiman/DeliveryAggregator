package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthAddressSignUpModel(
    @SerialName("geo_lat")
    val geoLat: String,
    @SerialName("geo_lon")
    val geoLon: String,
    val city: String,
    val street: String,
    val house: String
)
