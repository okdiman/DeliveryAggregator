package root.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressRequest(
    val city: String,
    @SerialName("geo_lat")
    val geoLat: String,
    @SerialName("geo_lon")
    val geoLon: String,
    val house: String,
    val street: String,
    val isActive: Boolean
)
