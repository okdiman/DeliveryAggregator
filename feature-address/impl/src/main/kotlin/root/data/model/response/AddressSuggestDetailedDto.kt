package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Suppress("LongParameterList")
class AddressSuggestDetailedDto(
    @SerialName("geo_lat")
    val geoLat: String,
    @SerialName("geo_lon")
    val geoLon: String,
    @SerialName("city_with_type")
    val city: String,
    @SerialName("city_district_with_type")
    val district: String,
    @SerialName("street_with_type")
    val street: String,
    @SerialName("block_type")
    val blockType: String,
    @SerialName("block")
    val block: String,
    @SerialName("house")
    val house: String,
    @SerialName("settlement_with_type")
    val settlement: String,
    @SerialName("area_with_type")
    val area: String
)
