package presentation.model

data class AddressUiModel(
    val value: String,
    val geoLat: String,
    val geoLon: String,
    val city: String,
    val street: String,
    val house: String,
    val subtitle: String
)