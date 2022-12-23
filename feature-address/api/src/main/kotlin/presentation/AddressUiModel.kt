package presentation

data class AddressUiModel(
    val value: String,
    val geo_lat: String,
    val geo_lon: String,
    val city: String,
    val street: String,
    val house: String,
    val subtitle: String
)