package orderdetails.root.data.model.extras

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import root.data.model.response.OrderExtrasDetailsDto

@Serializable
class ExtrasListDto(
    @SerialName("Extras")
    val extras: List<OrderExtrasDetailsDto>
)