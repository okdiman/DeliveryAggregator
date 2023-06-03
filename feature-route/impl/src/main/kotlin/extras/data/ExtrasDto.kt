package extras.data

import kotlinx.serialization.Serializable

@Serializable
data class ExtrasDto(
    val id: Long,
    val quantity: Int
)