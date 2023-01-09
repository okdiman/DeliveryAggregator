package data.model.request

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AddressRequest(
    val city: String,
    val comment: String,
    @SerializedName("geo_lat")
    val geoLat: String,
    @SerializedName("geo_lon")
    val geoLon: String,
    val house: String,
    val street: String,
    val isSelected: Boolean
)