package root.data.model.response

import com.google.gson.annotations.SerializedName

class AddressSuggestDto(
    @SerializedName("value")
    val value: String,
    @SerializedName("data")
    val data: AddressSuggestDetailedDto
)