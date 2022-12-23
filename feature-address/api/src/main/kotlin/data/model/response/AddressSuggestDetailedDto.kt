package data.model.response

import com.google.gson.annotations.SerializedName

class AddressSuggestDetailedDto(
    @SerializedName("geo_lat")
    val geo_lat: String,
    @SerializedName("geo_lon")
    val geo_lon: String,
    @SerializedName("city_with_type")
    val city: String,
    @SerializedName("city_district_with_type")
    val district: String,
    @SerializedName("street_with_type")
    val street: String,
    @SerializedName("block_type")
    val blockType: String,
    @SerializedName("block")
    val block: String,
    @SerializedName("house")
    val house: String,
    @SerializedName("settlement_with_type")
    val settlement: String,
    @SerializedName("area_with_type")
    val area: String
)