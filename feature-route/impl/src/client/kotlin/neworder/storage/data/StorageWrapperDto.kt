package neworder.storage.data

import com.google.gson.annotations.SerializedName
import root.data.model.response.OrderStorageDto

class StorageWrapperDto(
    @SerializedName("Storages")
    val request: List<OrderStorageDto>
)