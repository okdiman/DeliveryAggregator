package orderdetails.root.data.model.extras

import com.google.gson.annotations.SerializedName
import root.data.model.response.OrderExtrasDto

class ExtrasDto(
    @SerializedName("Extras")
    val extras: List<OrderExtrasDto>
)
