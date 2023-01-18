package root.data.model

import com.google.gson.annotations.SerializedName

class RouteDto(
    @SerializedName("requests")
    val orders: List<OrderDto>
)