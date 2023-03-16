package orderdetails.root.data.model.extras

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import root.data.model.response.OrderExtrasDto

@Serializable
class ExtrasDto(
    @SerialName("Extras")
    val extras: List<OrderExtrasDto>
)
