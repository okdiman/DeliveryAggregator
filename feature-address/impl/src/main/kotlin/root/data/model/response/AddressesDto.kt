package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import root.data.model.AddressDto

@Serializable
class AddressesDto(
    @SerialName("Addresses")
    val addresses: List<AddressDto>?
)
