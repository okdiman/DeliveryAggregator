package root.domain.model

import orderdetails.domain.model.OrderDetailsClientModel
import orderdetails.domain.model.OrderDetailsModel

data class RouteOrderModel(
    val order: OrderDetailsModel,
    val index: Int,
    val client: OrderDetailsClientModel
)