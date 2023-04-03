package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class AddressSuggestDto(
    @SerialName("value")
    val value: String,
    @SerialName("data")
    val data: AddressSuggestDetailedDto
)
