package root.data.model

import com.google.gson.annotations.SerializedName

class RouteDto(
    val id: Long,
    @SerializedName("clientRequest")
    val orders: List<RouteOrderDto>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalPrice")
    val totalPrice: Int,
    @SerializedName("distance")
    val distance: Double
)