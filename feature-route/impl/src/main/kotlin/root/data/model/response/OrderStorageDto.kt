package root.data.model.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
class OrderStorageDto(
    val address: String,
    val id: Int,
    val name: String,
    val weekWorkDays: List<String>,
    val dayOffs: JsonObject
)