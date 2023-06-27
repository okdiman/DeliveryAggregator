package root.data.model.response

import kotlinx.serialization.Serializable

@Serializable
class OrderExtrasDto(
    val extra: OrderExtrasDetailsDto,
    val quantity: Int = 0
)