package orderdetails.root.data.model.extras

import com.google.gson.annotations.SerializedName

class OrderDetailsPriceDescriptionDto(
    @SerializedName("String")
    val text: String,
    @SerializedName("Valid")
    val isValid: Boolean
)