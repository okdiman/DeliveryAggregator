package domain.model

data class AddressModel(
    val id: String,
    val city: String,
    val geoLat: String,
    val geoLon: String,
    val house: String,
    val street: String,
    val isActive: Boolean,
    val comment: String
)