package root.data.model

import com.google.gson.annotations.SerializedName

@Suppress("LongParameterList")
class AddressDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("geo_lat")
    val geoLat: String,
    @SerializedName("geo_lon")
    val geoLon: String,
    @SerializedName("house")
    val house: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("isActive")
    val isActive: Boolean,
    @SerializedName("comment")
    val comment: String,
)