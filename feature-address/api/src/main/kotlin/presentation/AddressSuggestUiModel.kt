package presentation

data class AddressSuggestUiModel(
    val value: String,
    val geoLat: String,
    val geoLon: String,
    val city: String,
    val street: String,
    val house: String,
    val subtitle: String,
    val comment: String = "",
    val isFinal: Boolean = true
)