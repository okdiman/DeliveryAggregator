package root.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Suppress("LongParameterList")
class AddressDto(
    @SerialName("id")
    val id: Long,
    @SerialName("city")
    val city: String,
    @SerialName("geo_lat")
    val geoLat: String,
    @SerialName("geo_lon")
    val geoLon: String,
    @SerialName("house")
    val house: String,
    @SerialName("street")
    val street: String,
    @SerialName("isActive")
    val isActive: Boolean
)
