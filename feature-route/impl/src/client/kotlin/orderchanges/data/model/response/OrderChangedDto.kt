package orderchanges.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import orderdetails.root.data.model.OrderDetailsDto

@Serializable
class OrderChangedDto(
    @SerialName("request")
    val order: OrderDetailsDto,
    @SerialName("changed")
    val changedOrder: OrderChangesDto,
)
