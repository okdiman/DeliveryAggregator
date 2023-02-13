package orderdetails.root.data.model.extras

import com.google.gson.annotations.SerializedName

class ExtrasDto(
    @SerializedName("Extras")
    val extras: List<OrderDetailsExtrasDto>
)