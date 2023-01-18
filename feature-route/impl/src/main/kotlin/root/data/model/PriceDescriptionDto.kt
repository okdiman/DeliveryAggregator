package root.data.model

import com.google.gson.annotations.SerializedName

class PriceDescriptionDto(
    @SerializedName("String")
    val text: String,
    @SerializedName("Valid")
    val isValid: Boolean
)