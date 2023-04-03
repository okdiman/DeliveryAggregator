package neworder.storage.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import root.data.model.response.OrderStorageDto

@Serializable
class StorageWrapperDto(
    @SerialName("Storages")
    val request: List<OrderStorageDto>
)
