package domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AddressSignUpModel(
    @SerializedName("geo_lat")
    val geoLat: String,
    @SerializedName("geo_lon")
    val geoLon: String,
    val city: String,
    val street: String,
    val house: String
)