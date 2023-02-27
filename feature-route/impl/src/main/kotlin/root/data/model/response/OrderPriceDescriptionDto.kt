package root.data.model.response

import com.google.gson.annotations.SerializedName

class OrderPriceDescriptionDto(
    @SerializedName("String")
    val text: String,
    @SerializedName("Valid")
    val isValid: Boolean
)