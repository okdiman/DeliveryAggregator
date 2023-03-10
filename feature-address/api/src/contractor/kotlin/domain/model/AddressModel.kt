package domain.model

data class AddressModel(
    val id: Long,
    val city: String,
    val geoLat: String,
    val geoLon: String,
    val house: String,
    val street: String,
    val isActive: Boolean
)
