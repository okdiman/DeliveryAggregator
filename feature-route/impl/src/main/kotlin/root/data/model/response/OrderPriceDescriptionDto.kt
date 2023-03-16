package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class OrderPriceDescriptionDto(
    @SerialName("String")
    val text: String,
    @SerialName("Valid")
    val isValid: Boolean
)
