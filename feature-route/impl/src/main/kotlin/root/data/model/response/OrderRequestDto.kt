package root.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderRequestDto(
    @SerialName("ContractorRequest")
    val order: OrderDto,
)
