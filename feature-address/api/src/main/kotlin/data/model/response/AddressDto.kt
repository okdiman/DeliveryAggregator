package data.model.response

import com.google.gson.annotations.SerializedName

@Suppress("LongParameterList")
class AddressDto(
    @SerializedName("userId")
    val id: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("geo_lat")
    val geoLat: String,
    @SerializedName("geo_lon")
    val geoLon: String,
    @SerializedName("house")
    val house: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("isSelected")
    val isSelected: Boolean
)