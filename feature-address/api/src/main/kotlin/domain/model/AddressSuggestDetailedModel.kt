package domain.model

data class AddressSuggestDetailedModel(
    val geoLat: String,
    val geoLon: String,
    val city: String,
    val district: String,
    val street: String,
    val house: String,
    val blockType: String,
    val block: String,
    val settlement: String,
    val area: String
)