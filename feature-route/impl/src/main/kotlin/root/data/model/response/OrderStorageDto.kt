package root.data.model.response

import kotlinx.serialization.Serializable

@Serializable
class OrderStorageDto(
    val address: String,
    val id: Int,
    val name: String
)
